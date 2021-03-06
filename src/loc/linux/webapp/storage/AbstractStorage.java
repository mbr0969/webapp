package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Resume;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

abstract public class AbstractStorage<C> implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected abstract void doDelete(C ctx);

    protected abstract C getContext(String uuid);
    protected abstract boolean exist(C ctx);

    protected abstract void doSave(C ctx, Resume r);

    protected abstract Resume doLoad(C ctx) throws IOException;

    protected abstract void doUpdate(C ctx, Resume r);

    protected abstract void doClear();

    @Override
    public void clear() {
        logger.info("Delete all resumes.");
        doClear();
    }


    @Override
    public void save(Resume r) {
        logger.info("Save resume with uuid =   " + r.getUuid());
        C ctx =getContext(r);
        if (exist(ctx)) {
            throw new WebAppExeption("Resume " + r.getUuid() + " already exist");
        }
        doSave(ctx,r);
    }

    @Override
    public void update(Resume r) {
        logger.info("Update resume with uuid = " + r.getUuid());
        C ctx =getContext(r.getUuid());
        if (!exist(ctx)) throw new WebAppExeption("Resume " + r.getUuid() + " not exist", r.getUuid());
        doUpdate(ctx,r);
    }



    @Override
    public Resume load(String uuid) throws IOException {
        logger.info("Load resume with uuid=" + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx)) {
            throw new WebAppExeption("Resume " + uuid + " is not exist");
        }
        return doLoad(ctx);
    }


    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid = " + uuid);
        C ctx =getContext(uuid);
        if (!exist(ctx) ) throw new WebAppExeption("Resume " + uuid + " not exist ");
        doDelete(ctx);
    }

    @Override
    public  Collection<Resume> getAllSorted() throws IOException {
        logger.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list, (Resume o1, Resume o2) -> {
            int cmp = o1.getFullName().compareTo(o2.getFullName());
            if (cmp != 0) return cmp;
            return o1.getUuid().compareTo(o2.getUuid());
        });

/*
        Collections.sort(list, (Resume o1, Resume o2) -> {
            int cmp = o1.getFullName().compareTo(o2.getFullName());
            if (cmp != 0) return cmp;
            return o1.getUuid().compareTo(o2.getUuid());
        });*/
     //   return Collections.singletonList(new Resume());

       // List<Resume> list = doGetAll();
      // Collections.sort(list);

     return list;
    }

    @Override
    public boolean isSectionSupported() {
        return true;
    }

    protected abstract List<Resume> doGetAll() throws IOException;

    public abstract int size();

    private C getContext(Resume r){
        return getContext(r.getUuid());
    }
}
