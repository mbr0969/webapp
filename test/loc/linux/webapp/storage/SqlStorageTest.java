package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppConfig;

/**
 * Created by papa on 11.03.2016.
 */
public class SqlStorageTest extends AbstractStorageTest{
    {
        storage = WebAppConfig.get().getStorage();
    }

}