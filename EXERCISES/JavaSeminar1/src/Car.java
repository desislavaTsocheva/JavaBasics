public class Car {
    private String model;
    private static int count=0;

    public Car(String model) {
        this.model=model;
        count++;
    }
    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Car car = new Car("Mercedes");
        Car car2=new Car("Mitsubishi");
        System.out.println("Car count is: "+Car.getCount());
    }
}
