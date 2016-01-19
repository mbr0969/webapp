package loc.linux.webapp.model;

/**
 * Created by papa on 19.01.16.
 */
public class Link {
//данные класса Link
    private static Link EMPTY = new Link();

    private final String name;
    private final String url;

// методы класса Link
    public Link() {
        this("",null);
    }

    public Link(Link link)
    {
        this(link.name,link.url);
    }

    public Link(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public static Link Empty()
    {
     return EMPTY;
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Link link = (Link) o;

        if (!name.equals(link.name)) return false;
        return url != null ? url.equals(link.url) : link.url == null;


    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
