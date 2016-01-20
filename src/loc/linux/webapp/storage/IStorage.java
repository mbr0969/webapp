package loc.linux.webapp.storage;

import loc.linux.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by papa on 20.01.2016.
 */
public interface IStorage {
    void clean();
    void save(Resume r);
    void update(Resume r);
    Resume load(String uuid);
    void delete(String uuid);
    Collection<Resume> getAllSorted();
    int size();
}
