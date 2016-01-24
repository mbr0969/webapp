package loc.linux.webapp.storage;

import loc.linux.webapp.model.Contact;
import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.*;

/**
 * Created by papa on 20.01.2016.
 */
public class ArrayStorageTest {
    private static Resume R1,R2,R3;
    private ArrayStorage storage = new ArrayStorage();

    static {
        R1 = new Resume("Mikle Brednew","Russia");
        R1.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R1.addContact(new Contact(ContactType.PHONE,"7710118"));
        R2 = new Resume("Cenia Grigoriev","Russia");
        R2.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R2.addContact(new Contact(ContactType.PHONE,"7710118"));
        R3 = new Resume("Masha Brednewa","Russia");
        R3.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R3.addContact(new Contact(ContactType.PHONE,"7710118"));

    }

    @BeforeClass
    public static void beforeClass(){

    }
    @Before
    public void before(){
        storage.clean();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @org.junit.Test
    public void testClean() throws Exception {

    }

    @org.junit.Test
    public void testSave() throws Exception {
        Assert.assertEquals(R1.getUuid(),R1.getUuid());

    }

    @org.junit.Test
    public void testUpdate() throws Exception {

    }

    @org.junit.Test
    public void testLoad() throws Exception {

    }

    @org.junit.Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2,storage.size());
        Assert.assertEquals(null,storage.load(R1.getUuid()));
    }

    @org.junit.Test
    public void testGetAllSorted() throws Exception {

    }

    @org.junit.Test
    public void testSize() throws Exception {
        Assert.assertEquals(3,storage.size());
    }
}