package loc.linux.webapp.model;

import loc.linux.webapp.web.SectionHtmlType;

/**
 * Created by papa on 19.01.16.
 */
public enum SectionType {
    OBJECTIVE("Позиция", SectionHtmlType.TEXT),
    ACHIEVEMENT("Достижения", SectionHtmlType.MULTI_TEXT),
    QUALIFICATIONS("Квалификация", SectionHtmlType.MULTI_TEXT),
    EXPERIENCE("Опыт работы", SectionHtmlType.ORGANIZATION),
    EDUCATION("Образование", SectionHtmlType.ORGANIZATION);


    private String title;
    private SectionHtmlType htmlType;

    SectionType(String title, SectionHtmlType htmlType) {
        this.title = title;
        this.htmlType = htmlType;
    }

    public SectionHtmlType getHtmlType() {
        return htmlType;
    }

    public String getTitle() {
        return title;
    }
}
