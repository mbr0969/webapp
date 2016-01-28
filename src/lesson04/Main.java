package lesson04;

import loc.linux.webapp.model.Organization;
import loc.linux.webapp.model.Resume;


import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {

        new Organization.Period();
        Map<String, Resume> map = new HashMap<>();
        map.put("uuid", new Resume("uuid", "", ""));
        for (Map.Entry<String, Resume> entry : map.entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

