package loc.linux.webapp.model;

/**
 * Created by papa on 19.01.16.
 */
public enum SectionType {
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;
//    private SectionType htmlType;

    SectionType(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
