package loc.linux.webapp.storage;

import java.lang.String;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Resume;

import java.util.Collection;

/**
 * Created by papa on 20.01.2016.
 */
public interface IStorage {
    void clean();
    void save(Resume r) throws WebAppExeption;
    void update(Resume r);
    void delete(String uuid);
    Resume load(String uuid);

    Collection<Resume> getAllSorted();
    int size();
}
