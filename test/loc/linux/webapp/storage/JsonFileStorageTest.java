package loc.linux.webapp.storage;

/**
 * Created by papa on 11.03.2016.
 */
public class JsonFileStorageTest extends  AbstractStorageTest{

    {
        storage = new JsonFileStorage("./file_storage");
    }

}