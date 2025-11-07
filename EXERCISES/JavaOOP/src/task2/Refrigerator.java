package task2;

public class Refrigerator extends Appliances {
    private double power;
    private double price;

    public Refrigerator(double power, double price) {
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
