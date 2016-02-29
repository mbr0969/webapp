package loc.linux.webapp.model;

import java.util.*;

/**
 * Created by papa on 19.01.16.
 */
public class Resume   { //implements Comparable<Resume>
    private String uuid;
    private String fullName;
    private String location;
    private String homePage;
    Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

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
    public void addContact(ContactType type,  String value) { contacts.put(type, value);
    }
    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    void addSection(SectionType type, Section section) {
        sections.put(type, section);
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
    public Section getSections(SectionType type) {
        return sections.get(type);
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

 //   @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.fullName);
    }

    /*private String getEmail(List<Contact> list) {
        for (Contact c: list ) {
            if (c.getType() == ContactType.MAIL){
                c.getValue();
            }
        }
*/

    @Override
    public String toString() {
        return "fullName= " + fullName + ", uuid= " + uuid ;
    }
}
