package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Resume;
import loc.linux.webapp.model.Section;
import loc.linux.webapp.model.SectionType;

import java.io.*;
import java.util.List;
import java.util.Map;


public abstract class FileStorage extends AbstractStorage<File> {
    private File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite())
            throw new IllegalArgumentException("'" + path + "' is not directory or is not writable");
    }

    @Override
    protected void doClear() {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected File getContext(String fileName) {
        return new File(dir, fileName);
    }

    @Override
    protected boolean exist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(File file, Resume r) {
        try{
            file.createNewFile();
            write(file, r);

        }catch (IOException e){
            throw new WebAppExeption("Could't create file " + file.getAbsolutePath(), r,e);
        }
        write(file, r);

    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new WebAppExeption("File " + file.getAbsolutePath() + " can not be deleted");
        }
    }

    @Override
    protected Resume doLoad(File file) {
        return read(file);

    }

    @Override
    protected void doUpdate(File file, Resume r) {
        write(file, r);

    }

    @Override
    protected List<Resume> doGetAll() {
        return null;
    }

    @Override
    public int size() {
        String[] list =dir.list();
        if (list == null) throw new WebAppExeption("Couldn't read directory " + dir.getAbsolutePath());
        return dir.list().length;
    }

   abstract protected void write(File file, Resume r);

   abstract protected Resume read(File file);
}
