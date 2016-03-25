package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Resume;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by papa on 20.01.2016.
 */
public interface IStorage {
    void clear();

    void save(Resume r) throws WebAppExeption;

    void update(Resume r);

    Resume load(String uuid) throws IOException;

    void delete(String uuid);

    Collection<Resume> getAllSorted() throws IOException;

    int size();

    boolean isSectionSupported();
}
