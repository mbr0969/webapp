package lesson03;

import loc.linux.webapp.model.Contact;
import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Link;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Contact contact = new Contact(ContactType.MOBILE,"+79045598062");
        System.out.println(contact.getType() + ":  " + contact.getValue());

        Field field = Link.class.getDeclaredField("url");
        field.setAccessible(true);
        Link link = new Link("Linux","papa.linux.loc");
        System.out.println(link.getName());
        System.out.println(field.get(link));
        System.out.println(link instanceof Link);

        StringBuffer fill = new StringBuffer();
        for (int i = 0; i< 100; i++){
            fill.append("a");
        }
        System.out.println(fill);
    }
}
