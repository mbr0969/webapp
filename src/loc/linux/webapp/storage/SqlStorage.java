package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Resume;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by papa on 23.03.2016.
 */
public class SqlStorage implements IStorage {

    public SqlStorage(String property, String   property1, String property2) {
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) throws WebAppExeption {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume load(String uuid) throws IOException {
        return null;
    }

    @Override
    public Collection<Resume> getAllSorted() throws IOException {
        return null;
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
