package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.Contact;
import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by papa on 28.01.2016.
 */
public class AbstractStorageTest {
    private Resume R1,R2,R3;
    private ArrayStorage storage = new ArrayStorage();

    /*static {
        R1 = new Resume("Mikle Brednew","Russia");
        R1.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R1.addContact(new Contact(ContactType.PHONE,"7710118"));
        R2 = new Resume("Cenia Grigoriev","Russia");
        R2.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R2.addContact(new Contact(ContactType.PHONE,"7710118"));
        R3 = new Resume("Masha Brednewa","Russia");
        R3.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R3.addContact(new Contact(ContactType.PHONE,"7710118"));
    }*/

    @BeforeClass
    public static void beforeClass(){

    }
    @Before
    public void before(){
        R1 = new Resume("Mikle Brednew","Russia");
        R1.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R1.addContact(new Contact(ContactType.PHONE,"7710118"));
        R2 = new Resume("Cenia Grigoriev","Russia");
        R2.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R2.addContact(new Contact(ContactType.PHONE,"7710118"));
        R3 = new Resume("Masha Brednewa","Russia");
        R3.addContact(new Contact(ContactType.MAIL,"mal@linux.ru"));
        R3.addContact(new Contact(ContactType.PHONE,"7710118"));
        storage.save(R2);
        storage.save(R1);
        storage.save(R3);
    }

    @Test
    public void testClean() throws Exception {
        storage.clean();
        assertEquals(0,storage.size());
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertEquals(R1.getUuid(),R1.getUuid());

    }

    @Test
    public void testUpdate() throws Exception {
        R2.setFullName("Update Name2");
        storage.update(R2);
        assertEquals(R2, storage.load(R2.getUuid()));


    }

    @Test
    public void testLoad() throws Exception {
        assertEquals(R1, storage.load(R1.getUuid()));
        assertEquals(R2, storage.load(R2.getUuid()));
        assertEquals(R3, storage.load(R3.getUuid()));
    }

    @Test(expected = WebAppExeption.class)
    public void testDeleteNotFound(){
        storage.load("Mama");
    }
    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2,storage.size());

    }

    @Test
    public void testGetAllSorted() throws Exception {
        Resume src[] = new Resume[]{R1,R2,R3};
        Arrays.sort(src);
        assertArrayEquals(src,storage.getAllSorted().toArray());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3,storage.size());
    }
}
