package loc.linux.webapp.storage;

import loc.linux.webapp.WebAppExeption;
import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Resume;
import loc.linux.webapp.model.SectionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by papa on 28.01.2016.
 */
abstract public class AbstractStorageTest {
    private Resume R1, R2, R3;
    protected IStorage storage;

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
    public static void beforeClass() {

    }

    @Before
    public void before() {
        R1 = new Resume("Masha Brednewa", "Russia");
        R1.addContact(ContactType.MAIL, "mal@linux.ru");
        R1.addContact(ContactType.PHONE, "7710118");
        R2 = new Resume("Mikle Brednew", "Russia");
        R2.addContact(ContactType.MAIL, "mal@linux.ru");
        R2.addContact(ContactType.PHONE, "7710118");
        R3 = new Resume("Cenia Grigoriev", "Russia");
        R3.addContact(ContactType.MAIL, "mal@linux.ru");
        R3.addContact(ContactType.PHONE, "7710118");
        R1.addObjective("Objective1");
        R2.addObjective("Objective1");
   //     R1.addMultiTextSection(SectionType.ACHIEVEMENT," Arhivment1", "Arhivment2");
  //      R1.addMultiTextSection(SectionType.QUALIFICATIONS,"JAVA","SQL");
        storage.clear();
        storage.save(R2);
        storage.save(R1);
        storage.save(R3);

    }

    @Test
    public void testClear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertEquals(R1.getUuid(), R1.getUuid());

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
    public void testDeleteNotFound() {
        storage.delete("Mama");
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R1.getUuid());
        Assert.assertEquals(2, storage.size());

    }

    @Test
    public void testGetAllSorted() throws Exception {
     /*   Resume[] src = new Resume[]{R1, R2, R3};
        Arrays.sort(src);
        assertArrayEquals(src, storage.getAllSorted().toArray());
    */
      List<Resume> list = Arrays.asList(R1, R2,R3);
       //Collections.sort(list);
        Collections.sort(list, (Resume o1, Resume o2) -> o1.getFullName().compareTo(o2.getFullName()));//new Comparator<Resume>() {


      //  assertEquals(list, new ArrayList<>(storage.getAllSorted()));
        assertEquals(list, storage.getAllSorted());
    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = WebAppExeption.class)
    public void testDeleteMissed() throws Exception {
        storage.delete("dummy");
    }

    @Test(expected = WebAppExeption.class)
    public void testSavePresented() throws Exception {
        storage.save(R1);
    }

    @Test(expected = WebAppExeption.class)
    public void testUpdateMissed() throws Exception {
        Resume resume = new Resume("dummy", "fullName_U1", "location_U1");
        storage.update(resume);
    }
}
