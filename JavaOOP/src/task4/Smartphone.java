package task4;

class Smartphone extends Devices implements Rechargeable {
    public Smartphone(String brand) {
        super(brand);
    }

    @Override
    public void turnOn() {
        System.out.println("Smartphone booting up.");
    }

    @Override
    public void recharge() {
        System.out.println("Smartphone recharged.");
    }
}
