package loc.linux.webapp.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by papa on 19.01.16.
 */
public class Resume {
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    List<Contact> contacts = new LinkedList<>();
    List<Section> sections = new LinkedList<>();

//Конструкторы класса Resume-------------
    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(),fullName,location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }
//-------------------------------------------------

//-------------Add Contacts and section-------------
    void addContact(Contact cantact){
        contacts.add(cantact);
    }

    void addSection(Section section){
        sections.add(section);
    }
//--------------------------------------------------

//------Getters class Resume-----

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Section> getSections() {
        return sections;
    }


//-------------------------------

// перорпеделение метдов equals и hashCode для класса Resume/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
//------------------------------------------

