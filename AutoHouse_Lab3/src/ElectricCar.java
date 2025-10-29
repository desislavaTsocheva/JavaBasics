public class ElectricCar extends Car implements Extras {
    private double batteryCapacity;
    public double getBatteryCapacity() {
        return batteryCapacity;
    }
    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public ElectricCar(String make, String model, int year, double engineCapacity, double price,
                       double batteryCapacityKWh, ACType acType, Interior interior, RimType rimType) {
        super(make, model, year, engineCapacity, price, acType, interior, rimType);
        this.batteryCapacity = batteryCapacityKWh;
    }


    @Override
    public double ecoTax(){
      if(batteryCapacity <=50){
        return 0;
      }
      else{
          return 50;
      }
    }
}
