package lesson03;

import loc.linux.webapp.model.Contact;
import loc.linux.webapp.model.ContactType;

/**
 * Created by papa on 19.01.2016.
 */
public class Main {
    public static void main(String[] args) {
        Contact contact = new Contact(ContactType.MOBILE,"+79045598062");
        System.out.println(contact.getType() + ":  " + contact.getValue());
    }
}
