package task2;

public abstract class WashingMachine extends Appliances {
    private double power;
    private double price;

    public WashingMachine(double power, double price) {
        this.power = power;
        this.price = price;
    }

    @Override
    public double getPowerUsages() {
        return power;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
