package lesson05;

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
    }

}
