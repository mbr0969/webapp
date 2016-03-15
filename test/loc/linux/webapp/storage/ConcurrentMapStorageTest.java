package loc.linux.webapp.storage;

import loc.linux.webapp.model.Resume;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

import static org.junit.Assert.*;

public class ConcurrentMapStorageTest {
    static {
        try(FileInputStream logProps = new FileInputStream(new File("logging.properties"))) {
            LogManager.getLogManager().readConfiguration(logProps);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testArrayStorage() throws Exception {
        run(new ArrayStorage());
    }

    @Test
    public void testSerializedFileStorage() throws Exception {
        run(new SerializeFileStorage(AbstractStorageTest.FILE_STORAGE));
    }

    @Test
    public void testSynchronizedMapStorage() throws Exception {
        run(new SynchronizedMapStorage());
    }

    @Test
    public void testConcurrentMapStorage() throws Exception {
        run(new ConcurrentMapStorage());
    }

    private void run(IStorage storage) throws Exception {
        for (int i = 1; i < 500; i++) {
            new Thread(() -> {
                Resume r = new Resume("name", "location");
                storage.save(r);
                try {
                    storage.load(r.getUuid());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                storage.delete(r.getUuid());
                try {
                    storage.getAllSorted();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(500);
    }

}