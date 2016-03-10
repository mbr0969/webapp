package loc.linux.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
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

    @Override
    public int hashCode() {
        return Objects.hash(link, periods);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        return Objects.equals(this.link, other.link) && Objects.equals(this.periods, other.periods);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link=" + link +
                ", periods=" + periods +
                '}';
    }


    //Внутренний класс период работы в оргнаицации


    public static class Period {
        private LocalDate StartDate;
        private LocalDate EndDate;
        private String position;
        private String content;

        public Period() {
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String position, String content){
            this(LocalDate.of(startYear, startMonth,1), LocalDate.of(endYear,endMonth,1),position,content);
        }

        public Period(LocalDate startDate, LocalDate endDate, String position, String content) {
            this.StartDate = startDate;
            this.EndDate = endDate;
            this.position = position;
            this.content = content;
            //link.getName();
        }
    }


}
