package lesson02;

import loc.linux.webapp.model.Link;

/**
 * Created by papa on 19.01.16.
 */
public class Main {
    public static void main(String[] args) {

        Link link1 = new Link("Papa","htts://vk.com/id14074974");
        Link link2 = link1;
        Link link3 = new Link(link1);
        System.out.println(link3.getClass());
        System.out.println(link1.equals(link2));
        System.out.println(link3.equals(link1));
        System.out.println(link1);
        System.out.println(link2);
        System.out.println(Link.Empty());
        System.out.println(link1.Empty());
    }
}
