package loc.linux.webapp.storage;

import java.lang.String;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;


public class ArrayStorage extends AbstractStorage {
    private static final int LIMIT = 100;

    private Resume[] array = new Resume[LIMIT];
    private int size = 0;
    //   int index = 0;


    @Override
    protected void doClear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    protected boolean exist(String uuid) {
        return getIndex(uuid) != -1;
    }

    @Override
    protected void doUpdate(Resume r) {
        int idx = getIndex(r.getUuid());
        if (idx == -1) throw new WebAppExeption("Resume " + r.getUuid() + " not exist");
        array[idx] = r;
    }

    @Override
    protected Resume doLoad(String uuid) {
        int idx = getIndex(uuid);
        if (idx == -1) throw new WebAppExeption("Resume " + uuid + "not exist");
        return array[idx];

    }

    @Override
    protected void doDelete(String uuid) {
        int idx = getIndex(uuid);
        if (idx == -1) throw new WebAppExeption("Resume " + uuid + "not exist");
        int numMoved = size - idx - 1;
        if (numMoved > 0)
            System.arraycopy(array, idx + 1, array, idx, numMoved);
        array[--size] = null; // clear to let GC do its work
    }

    @Override
    protected void doSave(Resume r) {
//        int idx = getIndex(r.getUuid());

       array[size++] = r;
    }

    @Override
    public int size() {
        return size;
    }

    //поиск в массиве резюме нужного элемента возварзщаем индекс массива
    public int getIndex(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                if (array[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }

   @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(Arrays.copyOf(array, size));
    }
}
