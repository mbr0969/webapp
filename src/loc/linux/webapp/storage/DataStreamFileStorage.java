package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class DataStreamFileStorage extends FileStorage {
    private static final String NULL = "null";

    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try (final DataOutputStream dos = new DataOutputStream(os)) {
            writeString(dos, resume.getUuid());
            writeString(dos, resume.getFullName());
            writeString(dos, resume.getLocation());
            writeString(dos, resume.getHomePage());
            Map<ContactType, String> contacts = resume.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos, entry.getValue());

            });

             Map<SectionType, Section> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case OBJECTIVE:
                        writeString(dos,((TextSection) section).getValue());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                             writeCollection(dos, ((MultiTextSection) section).getValues(), value -> writeString(dos,value));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
//                        writeCollection(dos, ((OrganizationSection) section).getValues(), (org) -> {
//                            dos.writeUTF(org.getLink().getName());
//                            dos.writeUTF(org.getLink().getUrl());
//                            writeCollection(dos, org.getPeriods(), period -> {
//                                DataStreamFileStorage.this.writeLocalDate(dos, period.getStartDate());
//                                DataStreamFileStorage.this.writeLocalDate(dos, period.getEndDate());
//                                dos.writeUTF(period.getPosition());
//                                dos.writeUTF(period.getContent());
//                            });
//                        });
                       break;
                }
            }
        } catch (IOException e) {
            throw new WebAppExeption("Could not not write file " + os.getClass(), resume, e);
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(is)) {
            r.setUuid(readString(dis));
            r.setFullName(readString(dis));
            r.setLocation(readString(dis));
            r.setHomePage(readString(dis));
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                r.addContact(ContactType.VALUES[dis.readInt()], readString(dis));
            }
            final int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                        r.addObjective(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        r.addSection(sectionType, new MultiTextSection(readList(dis, () -> readString(dis))));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
//                        r.addSection(sectionType,
//                                new OrganizationSection(readList(dis, () -> new Organization(new Link(dis.readUTF(), dis.readUTF()),
//                                        readList(dis, () -> new Organization.Period(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()))))));
                        break;
//
                }
            }
            return r;
        }

    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        Objects.requireNonNull(ld, "LocalDate cannot be null, use Period.NOW");
        dos.writeInt(ld.getYear());
        dos.writeUTF(ld.getMonth().name());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), Month.valueOf(dis.readUTF()), 1);
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void writeString(DataOutputStream dos, String str) throws IOException {
        dos.writeUTF(str == null ? NULL : str);
    }

    private String readString(DataInputStream dis) throws IOException {
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }
}