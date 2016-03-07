package loc.linux.webapp.storage;

public class SerializeFileStorageTest extends AbstractStorageTest {
    {
        storage = new SerializeFileStorage("./file_storage");
    }
}
