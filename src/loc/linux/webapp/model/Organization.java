package loc.linux.webapp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by papa on 19.01.16.
 */
public class Organization {
    static final long serialVersionUID = 1L;
    private String name;
    private Link link;
    List<Period> periods;
    List<String> position;

    public Organization() {
    }

    public Organization(String name, Link link) {
        this.name = name;
        this.link = link;
    }
    public String getName() {
        return name;
    }

    //Внутренний класс период работы в оргнаицации
    public static class Period {
        private Date StartDate;
        private Date EndDate;
        private String position;
        private String content;

        public Period() {
        }

        public Period(Date startDate, Date endDate, String position, String content) {
            StartDate = startDate;
            EndDate = endDate;
            this.position = position;
            this.content = content;
            //link.getName();
        }
    }


}
