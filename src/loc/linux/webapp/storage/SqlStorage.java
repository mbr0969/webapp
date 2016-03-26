package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Resume;
import loc.linux.webapp.model.Section;
import loc.linux.webapp.model.SectionType;
import loc.linux.webapp.sql.Sql;
import loc.linux.webapp.sql.SqlExecutor;
import loc.linux.webapp.sql.SqlTransaction;
import loc.linux.webapp.util.Util;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class SqlStorage implements IStorage {
    public Sql sql;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        sql = new Sql(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sql.execute("DELETE FROM resume");

    }

    @Override
    public void save(final Resume r) throws WebAppExeption {

        sql.execute((SqlTransaction<Void>) conn -> {

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name, location," +
                    " home_page) VALUES(?,?,?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.setString(3, r.getLocation());
                ps.setString(4, r.getHomePage());
                ps.execute();
                insertContact(conn, r);
          //      insertSections(conn,r);
                return null;
            }
        });
    }

    @Override
    public void update(Resume r) {

        sql.execute(conn -> {

            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name=?, location=?," +
                    " home_page =? WHERE uuid=?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getLocation());
                ps.setString(3, r.getHomePage());
                ps.setString(4, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new WebAppExeption("Resume not found", r);
                }
            }
            deleteContacts(conn, r);
            insertContact(conn, r);
     //   deleteSections(conn,r);
      //      insertSections(conn,r);
            return null;
        });
    }


    @Override
    public void delete(String uuid) {

        sql.execute("DELETE FROM resume WHERE uuid=?", (SqlExecutor<Void>) ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new WebAppExeption("Resume " + uuid + "not exist", uuid);
            }
            return null;
        });

    }

    @Override
    public Resume load(String uuid) throws IOException {
        //SELECT * FROM resume r LEFT JOIN contact c ON  c.resume_uuid=r.uuid
        // LEFT JOIN text_section tc ON r.uuid = tc.resume_uuid
       // WHERE r.uuid =

        return sql.execute(" " +/*
                "    SELECT * FROM resume r LEFT JOIN contact c ON  c.resume_uuid=r.uuid " +
                "     LEFT JOIN text_section tc ON r.uuid = tc.resume_uuid  WHERE r.uuid =?",
*/

                        "SELECT *\n" +
                        "  FROM resume r\n" +
                        "  LEFT JOIN contact c ON c.resume_uuid=r.uuid\n" +
                        " WHERE r.uuid = ?",
                st -> {
                    st.setString(1, uuid);
                    ResultSet rs = st.executeQuery();
                    if (!rs.next()) {
                        throw new WebAppExeption("Resume " + uuid + " is not exist");
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"), rs.getString("location"), rs.getString("home_page"));
                    addContact(rs, r);
                    while (rs.next()) {
                        addContact(rs, r);
                    }
    /*   *//*             addSection(rs,r);
                    while (rs.next()){
                        addSection(rs,r);*//*
                    }*/

                    return r;
                });
    }

    @Override
    public Collection<Resume> getAllSorted() throws IOException {
        return sql.execute("SELECT * FROM resume r LEFT JOIN contact c ON r.uuid = c.resume_uuid  " +
                "LEFT JOIN text_section tc ON r.uuid = tc.resume_uuid ORDER BY full_name, uuid",
                ps -> {
                    ResultSet rs = ps.executeQuery();
                    Map<String, Resume> map = new LinkedHashMap<>();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        Resume resume = map.get(uuid);
                        if (resume == null) {
                            resume = new Resume(uuid, rs.getString("full_name"),
                                    rs.getString("location"), rs.getString("home_page"));
                            map.put(uuid, resume);
                        }
                        addContact(rs, resume);
                //        addSection(rs,resume);
                    }
                    return map.values();
                });
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }

    @Override
    public int size() {
        return sql.execute("SELECT  count(*) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        if (!Util.isEmpty(value)) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            r.addContact(type, value);
        }
    }

    private void addSection(ResultSet rs, Resume r )throws SQLException{
        String value = rs.getString("value");
        if(!Util.isEmpty(value)){
            SectionType type =SectionType.valueOf(rs.getString("type"));
            r.addSection(type,type.getHtmlType().createSection(value));
        }

    }

    private void insertContact(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                st.setString(1, r.getUuid());
                st.setString(2, e.getKey().name());
                st.setString(3, e.getValue());
                st.addBatch();
            }
            st.executeBatch();
        }
    }
    private void insertSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("INSERT INTO text_section (resume_uuid, type," +
                " values) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, Section> e : r.getSections().entrySet()) {
                st.setString(1, r.getUuid());
                st.setString(2, e.getKey().name());
                st.setString(3, String.valueOf(e.getValue()));
                st.addBatch();
            }
            st.executeBatch();
        }
    }

    private void deleteContacts(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid=?")) {
            st.setString(1, r.getUuid());
            st.execute();
        }
    }
    private void deleteSections(Connection conn, Resume r) throws SQLException {
        try (PreparedStatement st = conn.prepareStatement("DELETE FROM text_section WHERE resume_uuid=?")) {
            st.setString(1, r.getUuid());
            st.execute();
        }
    }



    void insertDate(LocalDate startDate, LocalDate endDate) {
        sql.execute("INSERT INTO period (start_date, end_date) VALUES (?,?)",
                st -> {
                    st.setDate(1, java.sql.Date.valueOf(startDate));
                    st.setDate(2, java.sql.Date.valueOf(endDate));
                    st.execute();
                    return null;
                });
    }
}
