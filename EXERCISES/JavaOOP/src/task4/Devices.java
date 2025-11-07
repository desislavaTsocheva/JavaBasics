package task4;

abstract class Devices {
    protected String brand;

    public Devices(String brand) {
        this.brand = brand;
    }

    public abstract void turnOn();

    public void turnOff() {
        System.out.println("Device turned off.");
    }
}
