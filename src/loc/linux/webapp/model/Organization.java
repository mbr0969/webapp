package loc.linux.webapp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by papa on 19.01.16.
 */
public class Organization {
    private  String name;
    private Link link;
    List<OrganizationPeriod> periods;
    List<String> position;

    public Organization(String name, Link link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    //public String getUrl() {
//        return url;
//    }

//    public String getAdress() {
//        return adress;
//    }
//
}
