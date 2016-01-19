package loc.linux.webapp.model;

/**
 * Created by papa on 19.01.16.
 */
public class Contact {
    private final ContactType type;
    private final String value;

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ContactType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
