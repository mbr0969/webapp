package loc.linux.webapp.storage;

import loc.linux.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by papa on 20.01.2016.
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 10000;
    private Resume[]  array = new Resume[LIMIT];
    private int size = 0;


    @Override
    public void clean() {
        Arrays.fill(array,null);
        size = 0;
    }

    @Override
    public void save(Resume r) {
        array[size++] = r;

    }

    @Override
    public void update(Resume r, Integer index) {
        array[index] = r;
    }

    @Override
    public Resume load(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        size = array.length;
        return size;
    }
}
