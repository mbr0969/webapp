package lesson03;

import loc.linux.webapp.model.Resume;

/**
 * Created by papa on 20.01.2016.
 */
public class MainArray {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3};
        System.out.println(array.length);
        System.out.println(array.getClass());

        Resume[] resumes = new Resume[10];
        System.out.println(resumes.length);
        System.out.println(resumes.getClass());
    }
}
