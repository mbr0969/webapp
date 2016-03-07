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

    public DataStreamFileStorage(String path) {
        super(path);
    }


    protected void write(File file, Resume r) {
        try (FileOutputStream fos = new FileOutputStream(file); DataOutputStream dos = new DataOutputStream(fos);){
            dos.writeUTF(r.getFullName());
            dos.writeUTF(r.getLocation());
            dos.writeUTF(r.getHomePage());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                dos.writeUTF(entry.getValue());
            }
            for (Map.Entry<SectionType, Section> entry : r.getSections().entrySet()) {
                dos.writeInt(entry.getKey().ordinal());
                dos.writeUTF(entry.toString());
            }
        }catch (IOException e){
            throw new WebAppExeption("Could't create file " + file.getAbsolutePath(), r,e);
        }
    }

    protected Resume read(File file){
        Resume r = new Resume();
        try (InputStream is = new FileInputStream(file); DataInputStream dis = new DataInputStream(is)){
            r.setFullName(dis.readUTF());
            r.setLocation(dis.readUTF());
            r.setHomePage(dis.readUTF());
            int contactsSize = dis.readInt();
            for (int i = 0; i< contactsSize;i++){
                r.addContact(ContactType.VALUES[dis.readInt()], dis.readUTF());
            }

        }catch (IOException e){
            throw new WebAppExeption("Couldn't read file " + file.getAbsolutePath(),e);
        }
        return null;
    }

}
