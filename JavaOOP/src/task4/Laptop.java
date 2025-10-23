package task4;

class Laptop extends Devices implements Rechargeable {
    public Laptop(String brand) {
        super(brand);
    }

    @Override
    public void turnOn() {
        System.out.println("Laptop starting.");
    }

    @Override
    public void recharge() {
        System.out.println("Laptop battery full.");
    }
}
