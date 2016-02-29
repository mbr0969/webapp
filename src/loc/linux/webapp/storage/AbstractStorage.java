package loc.linux.webapp.storage;

import loc.linux.webapp.*;
import loc.linux.webapp.model.Resume;

import java.util.*;
import java.util.logging.Logger;

abstract public class AbstractStorage implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected abstract void doDelete(String uuid);

    protected abstract boolean exist(String uuid);

    protected abstract void doSave(Resume r);

    protected abstract Resume doLoad(String uuid);

    protected abstract void doUpdate(Resume r);

    protected abstract void doClear();

    @Override
    public void clear() {
        logger.info("Delete all resumes.");
        doClear();
    }


    @Override
    public void save(Resume r) {
        logger.info("Save resume with uuid =   " + r.getUuid());
        if (exist(r.getUuid())) throw new WebAppExeption("Resume " + r.getUuid() + " already exist");
        doSave(r);
    }

    @Override
    public void update(Resume r) {
        logger.info("Update resume with uuid = " + r.getUuid());
        if (!exist(r.getUuid())) throw new WebAppExeption("Resume " + r.getUuid() + " not exist", r.getUuid());
        doUpdate(r);
    }


    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with uuid=" + uuid);
        if (!exist(uuid)) throw new WebAppExeption("Resume " + uuid + " not exist", uuid);
        return doLoad(uuid);
    }


    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid = " + uuid);
        if (!exist(uuid)) throw new WebAppExeption("Resume " + uuid + "not exist");
        doDelete(uuid);
    }

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("getAllSorted");
        List<Resume> list = doGetAll();

        Collections.sort(list, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                int cmp = o1.getFullName().compareTo(o2.getFullName());
                if (cmp != 0) return cmp;
                return o1.getUuid().compareTo(o2.getUuid());
            }
        });
      /* Collections.sort(list, (Resume o1, Resume o2) -> {

            int cmp = o1.getFullName().compareTo(o2.getFullName());
            if (cmp != 0) return cmp;
            return o1.getUuid().compareTo(o2.getUuid());
        });*/
        return list;
    }

    @Override
    public boolean isSectionSupported() {
        return true;
    }

    protected abstract List<Resume> doGetAll();

    public abstract int size();
}
