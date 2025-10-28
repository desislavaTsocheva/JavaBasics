public class DieselCar extends Car {
    @Override
    public double ecoTax(){
        return 300+0.10*engineCapacity;
    }
}
