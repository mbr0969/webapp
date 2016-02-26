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


        System.out.println(new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2){
                System.out.println(this.getClass().getName());
                return 0;
            }
        });
    }

}
