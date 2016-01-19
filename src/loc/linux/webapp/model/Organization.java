package loc.linux.webapp.model;

/**
 * Created by papa on 19.01.16.
 */
public class Organization {
    private final String name;
    private final String url;
    private final String adress;

    public Organization(String name, String url, String adress) {
        this.name = name;
        this.url = url;
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getAdress() {
        return adress;
    }
}
