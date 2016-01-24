package loc.linux.webapp.storage;

import loc.linux.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by papa on 20.01.2016.
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    private Resume[] array = new Resume[LIMIT];
    private int size;
    int index = 0;


    @Override
    public void clean() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public void save(Resume r) {
//        int idx = -1;
//        for(int i = 0; i<LIMIT; i++) {
//            Resume resume = array[i];
//            if (resume != null) {
//                if (r.equals(resume)){
//                    throw new IllegalStateException("Already exist");
//                }
//            }else if (idx == -1){
//                idx = i;
//            }
//        }
//        array[idx] = r;

        for (int i = 0; i < LIMIT; i++) {
            if (array[i] == null) {
                array[i] = r;
                }
        }
        array[size++] = r;
    }

    @Override
    public void update(Resume r) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i].getUuid().equals(r.getUuid())) {
                array[i] = r;
            } else System.out.println("Not Resume exist");
        }
    }

    @Override
    public Resume load(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (array[i].getUuid().equals(uuid)) {
                return array[i];
            }
        }
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
        return size;
    }
}
