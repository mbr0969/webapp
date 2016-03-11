package loc.linux.webapp.storage;

/**
 * Created by papa on 11.03.2016.
 */
public class SerializeFileStorageTest extends AbstractStorageTest{
    {
        storage = new SerializeFileStorage("./file_storage");
    }

}