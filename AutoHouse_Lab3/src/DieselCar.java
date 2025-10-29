public class DieselCar extends Car {

    public DieselCar(String make, String model, int year, double engineCapacity, double price,
                     ACType acType, Interior interior, RimType rimType) {
        super(make, model, year, engineCapacity, price, acType, interior, rimType);
    }
    @Override
    public double ecoTax(){
        return 300+0.10*engineCapacity;
    }
}
