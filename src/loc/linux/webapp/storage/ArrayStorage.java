package loc.linux.webapp.storage;

import  java.lang.String;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by papa on 20.01.2016.
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    //    protected Logger LOGGER = Logger.getLogger(getClass().getName());
    private static Logger LOGGER = Logger.getLogger(ArrayStorage.class.getName());

    private Resume[] array = new Resume[LIMIT];
    private int size = 0;
    //   int index = 0;


    @Override
    public void clean() {
        LOGGER.info("Delete all resume");
        Arrays.fill(array, null);
        size = 0;
    }


    @Override
    public void save(Resume r) {
        LOGGER.info("Save resome  with UUID: " + r.getUuid());
        int idx = getIndex(r.getUuid());

/*         if (idx != -1){
              try {
                throw new WebAppExeption("Resume " + r.getUuid() + "alredy exist", r);
            } catch (WebAppExeption e) {
                LOGGER.log(Level.SEVERE, e.getMessage(),e);
                throw new IllegalStateException(e);
            }*/

        if (idx != -1) throw new WebAppExeption("Resume " + r.getUuid() + "alredy exist", r);
        array[size++] = r;

    }


    @Override
    public void update(Resume r) {
        LOGGER.info("Update resume with " + r.getUuid());
        int idx = getIndex(r.getUuid());
        if (idx != -1 ) throw new WebAppExeption("Resume " + r.getUuid() + "not exist", r);
        array[idx] = r;

    }

    @Override
    public Resume load(String uuid) {
        LOGGER.info("Load resume with UUID " + uuid);
        int idx = getIndex(uuid);
        if (idx != -1 ) throw new WebAppExeption("Resume " + uuid + "not exist");
        return array[idx];
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete resume with UUID " + uuid);
        int idx = getIndex(uuid);
        if (idx != -1 ) throw new WebAppExeption("Resume " + uuid + "not exist");

        int numMoved = size - idx - 1;
        if (numMoved > 0)
         System.arraycopy(array, idx+1, array, idx, numMoved);
        array[--size] = null; // clear to let GC do its work


    }

    @Override
    public Collection<Resume> getAllSorted() {
        Arrays.sort(array,0,size);
        return Arrays.asList(Arrays.copyOf(array,size));
    }

    @Override
    public int size() {
        return size;
    }

    //поиск в массиве резюме нужного элемента возварзщаем индекс массива
    public int getIndex(String uuid){
        for (int i =0; i< LIMIT;i++) {
            if(array[i] != null){
               if (array[i].getUuid().equals(uuid)){
                   return i;
               }
            }
        }
        return -1;
    }
}
