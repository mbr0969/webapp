package loc.linux.webapp.storage;

import loc.linux.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by papa on 28.01.2016.
 */
public class MapStorage extends AbstractStorage{


    @Override
    protected void doSave(Resume r) {

    }

    @Override
    public void clean() {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume load(String uuid) {
        return null;
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
