package loc.linux.webapp.model;

/**
 * Created by papa on 19.01.2016.
 */
public enum ContactType {
    PHONE("Тел."),
    MOBILE("Мобильный:"),
    HOME_PHONE("Домашний."),
    SKYPE("Скайп"),
    MAIL("MAIL"),
    ICQ("ICQ");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public static ContactType[] VALUES = ContactType.values();
}
