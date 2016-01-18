package main;

/**
 * Created by papa on 19.01.16.
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello %s!\n", args[0]);
        Car raceCar = new RaceCar();
        Car simpleCar = new SimpleCar();
        System.out.println(raceCar.getSpeed());
        raceCar.getDescription();
        simpleCar.getDescription();
    }
}
