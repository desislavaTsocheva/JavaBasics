package task4;

public class MainTask4 {
    public static void main(String[] args) {
        Devices[] devices = new Devices[2];
        devices[0] = new Smartphone("Samsung");
        devices[1] = new Laptop("Dell");

        for (Devices device : devices) {
            device.turnOn();
            device.turnOff();
            if (device instanceof Rechargeable rechargeableDevice) {
                rechargeableDevice.recharge();
            }
        }
    }
}
