package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Resume;

import java.util.logging.Logger;


abstract class AbstractStorage implements IStorage {
    protected  Logger logger  = Logger.getLogger(ArrayStorage.class.getName());

    @Override
    public void save(Resume r) {
        logger.info("Save resume  with UUID: " + r.getUuid());
        doSave(r);
    }

    protected abstract void doSave(Resume r);
}
