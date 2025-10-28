public class ElectricCar extends Car implements Extras {
    private double batteryCapacity;
    public double getBatteryCapacity() {
        return batteryCapacity;
    }
    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
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
