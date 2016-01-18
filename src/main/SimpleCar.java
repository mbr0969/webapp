package main;

/**
 * Created by papa on 19.01.16.
 */
public class SimpleCar extends AbstractCar{

    @Override
    public void getDescription() {
        super.getDescription();
        System.out.println("Something custom");
    }

    @Override
    public double getEngineVolume() {
        return 1.7;
    }
}
