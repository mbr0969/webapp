package loc.linux.webapp.model;

import java.io.Serializable;

public enum ContactType implements Serializable{
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
