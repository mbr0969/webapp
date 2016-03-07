package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Resume;
import loc.linux.webapp.model.Section;
import loc.linux.webapp.model.SectionType;

import java.io.*;
import java.util.List;
import java.util.Map;


public class DataStreamFileStorage extends FileStorage {
    private static final String NULL= "null";

    public DataStreamFileStorage(String path) {
        super(path);
    }
    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try (final DataOutputStream dos = new DataOutputStream(os)){
            writeString(dos, resume.getFullName());
            writeString(dos, resume.getLocation());
            writeString(dos, resume.getHomePage());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                writeString(dos,entry.getValue());
            }
            Map<SectionType, Section> sections = resume.getSections();
           for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(dos, type.name());
            }
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        Resume r = new Resume();
        try (DataInputStream dis = new DataInputStream(is)){
            r.setUuid(dis.readUTF());
            r.setFullName(readString(dis));
            r.setLocation(readString(dis));
            r.setHomePage(readString(dis));
            int contactsSize = dis.readInt();
            for (int i = 0; i< contactsSize;i++){
                r.addContact(ContactType.VALUES[dis.readInt()], readString(dis));
            }

        }
        return r;
    }

    private void writeString(DataOutputStream dos, String str ) throws IOException{
        dos.writeUTF(str == null ? NULL : str );
    }

    private String readString(DataInputStream dis) throws  IOException{
        String str = dis.readUTF();
        return str.equals(NULL) ? null : str;
    }

}
