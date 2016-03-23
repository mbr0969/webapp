package loc.linux.webapp.web;

import loc.linux.webapp.model.ContactType;
import loc.linux.webapp.model.Organization;
import loc.linux.webapp.model.Resume;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HtmlUtil {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    public static final String EMPTY_TD = "<img src='img/s.gif'>";

    public static String getContact(Resume r, ContactType type) {
        String contact = r.getContact(type);
        return contact == null ? "&nbsp;" : type.toHtml(contact);
    }

        public static String format(LocalDate date) {
            return date.equals(Organization.Period.NOW) ? "Сейчас" : date.format(DATE_FORMATTER);
        }
    }

