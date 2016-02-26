package lesson05;

import loc.linux.webapp.model.Resume;

import java.util.Comparator;

public class Calculator {
    public int abc(int value) {
        return (value < 0) ? -value : value;   //Math.abs(value);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        int value = -5;
        System.out.println(value);
        int result = calc.abc(value);
        System.out.println(result);

        Comparator<Resume> comparator = new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                System.out.printf(this.getClass().getSimpleName());
                return 0;
            }
        };

        System.out.println(comparator);
    }

}
