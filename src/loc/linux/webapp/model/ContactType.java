package loc.linux.webapp.model;

import java.io.Serializable;

public enum ContactType implements Serializable{
    PHONE("Тел."),
    MOBILE("Мобильный:"),
    HOME_PHONE("Домашний."),
    SKYPE("Skype"){
        @Override
        public String toHtml(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    MAIL("Почта") {
        @Override
        public String toHtml(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
    ICQ("ICQ");

     private String title;
     public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }


    public static ContactType[] VALUES = ContactType.values();

    public String toHtml(String value) {
        return title + ": " + value;
    }
}
