package loc.linux.webapp.web;

import loc.linux.webapp.model.MultiTextSection;
import loc.linux.webapp.model.Section;
import loc.linux.webapp.model.SectionType;
import loc.linux.webapp.model.TextSection;

import java.util.Collections;

import static loc.linux.webapp.web.HtmlUtil.textArea;

public enum SectionHtmlType {
    TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return input(type.name(), section == null ? "" : ((TextSection) section).getValue());
        }

        @Override
        public TextSection createSection(String value) {
            return new TextSection(value);
        }
    },
    MULTI_TEXT {
        @Override
        public String toHtml(Section section, SectionType type) {
            return textArea(type.name(), section == null ? Collections.singletonList("") :((MultiTextSection) section).getValues());
        }

        @Override
        public MultiTextSection createSection(String values) {
            return new MultiTextSection(values.split("\\n"));
        }
    },
    ORGANIZATION {
        @Override
        public String toHtml(Section section, SectionType type) {
            return section.toString();
        }

        @Override
        public Section createSection(String value) {
            throw new UnsupportedOperationException();
        }
    };

    public static String input(String name, String value) {
        return String.format("<input type='text' name='%s' size=75 value='%s'>", name, value);
    }

    public abstract String toHtml(Section section, SectionType type);

    public abstract Section createSection(String value);

}
