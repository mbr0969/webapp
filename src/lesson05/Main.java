package lesson05;

import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Resume;

import java.util.Comparator;

/**
 * Created by Tata on 27.02.2016.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        int value = -5;
        System.out.println(value);
        int result = calc.abs(value);
        System.out.println(result);

        Comparator<Resume> comparator = new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                System.out.printf(this.getClass().getSimpleName());
                return 0;
            }
        };

        System.out.println(comparator);

        Resume R1 = new Resume("Mikle Brednew", "Russia");
        R1.addContact(ContactType.MAIL, "mal@linux.ru");
        R1.addContact(ContactType.PHONE, "7710118");
        Resume R2 = new Resume("Cenia Grigoriev", "Russia");
        R2.addContact(ContactType.MAIL, "mal@linux.ru");
        R2.addContact(ContactType.PHONE, "7710118");
        Resume R3 = new Resume("Masha Brednewa", "Russia");
        R3.addContact(ContactType.MAIL, "mal@linux.ru");
        R3.addContact(ContactType.PHONE, "7710118");

        Resume r2 = new Resume(){
            @Override
            public String getUuid() {
                return "uuid_r2";
            }
        };

          print(r2);
    }

    private static <T extends  Resume> void print(T e ) {
        System.out.println(e.getClass());
        //   list.forEach(r ->System.out.println(r.toString()) );
    }


}
