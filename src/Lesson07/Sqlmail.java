package Lesson07;

import java.sql.*;

public class Sqlmail {
    public static void main(String[] args) throws SQLException {
        Connection dbh = DriverManager.getConnection("jdbc:postgresql://localhost:5432/base",
                "postgres", "user");
        Statement st = dbh.createStatement();

        ResultSet resultSet = st.executeQuery("SELECT * FROM resume");

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
        }
        resultSet.close();
        st.close();
    }
}
