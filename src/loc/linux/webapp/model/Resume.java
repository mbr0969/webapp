package loc.linux.webapp.model;

import java.util.*;

/**
 * Created by papa on 19.01.16.
 */
public class Resume implements Comparable<Resume>{
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

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(location, resume.location) &&
                Objects.equals(homePage, resume.homePage) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(sections, resume.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, location, homePage, contacts, sections);
    }

    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }
}