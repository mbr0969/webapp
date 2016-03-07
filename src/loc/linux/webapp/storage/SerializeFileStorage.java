package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Resume;
import loc.linux.webapp.model.Section;
import loc.linux.webapp.model.SectionType;

import java.io.*;
import java.util.Map;


public class SerializeFileStorage extends FileStorage {


    public SerializeFileStorage(String path) {
        super(path);
    }


    protected void write(File file, Resume r) {
        try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos);){
            oos.writeObject(r);
        }catch (IOException e){
            throw new WebAppExeption("Could't create file " + file.getAbsolutePath(), r,e);
        }
    }

    protected Resume read(File file)  {
        Resume r = new Resume();
        try (InputStream is = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(is)){
            return (Resume) ois.readObject();

        }catch (IOException e){
            throw new WebAppExeption("Couldn't read file " + file.getAbsolutePath(),e);
        } catch (ClassNotFoundException e) {
            throw new WebAppExeption("Error read resume.");
        }

    }
}
