package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Resume;

import java.util.logging.Logger;

/**
 * Created by papa on 27.01.16.
 */
abstract class AbstractStorage implements IStorage {
    protected  Logger logger  = Logger.getLogger(ArrayStorage.class.getName());

    @Override
    public void save(Resume r) {
        logger.info("Save resume  with UUID: " + r.getUuid());
        doSave(r);
    }

    protected abstract void doSave(Resume r);
}
