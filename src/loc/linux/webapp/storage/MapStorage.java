package loc.linux.webapp.storage;

import loc.linux.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> MAP = new HashMap<>();

    @Override
    protected String getContext(String uuid) {
        return uuid;
    }

    @Override
    protected void doClear() {
        MAP.clear();
    }

    @Override
    protected boolean exist(String uuid) {
        return MAP.containsKey(uuid);
    }

    @Override
    protected void doSave(String uuid, Resume r) {
        MAP.put(r.getUuid(), r);
    }

    @Override
    public void doUpdate(String uuid, Resume r) {
        MAP.put(r.getUuid(), r);
    }

    @Override
    public Resume doLoad(String uuid) {
        return MAP.get(uuid);
    }

    @Override
    public void doDelete(String uuid) {
        MAP.remove(uuid);
    }

    @Override
    public List<Resume> doGetAll() {
        return new ArrayList<>(MAP.values());
    }

    @Override
    public int size() {
        return MAP.size();
    }
}
