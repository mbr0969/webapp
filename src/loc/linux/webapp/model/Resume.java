package loc.linux.webapp.model;

import java.util.*;

/**
 * Created by papa on 19.01.16.
 */
public class Resume implements Comparable<Resume> {
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    List<Contact> contacts = new LinkedList<>();
    List<Section> sections = new LinkedList<>();

    //Конструкторы класса Resume-------------
    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }

    public Resume() {   }
//-------------------------------------------------

    //-------------Add Contacts and section-------------
    public void addContact(Contact cantact) {
        contacts.add(cantact);
    }

    void addSection(Section section) {
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

// ----------Setters

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }


// перорпеделение метдов equals и hashCode для класса Resume/

//------------------------------------------


    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        return uuid.equals(other.uuid);
    }

    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }
}