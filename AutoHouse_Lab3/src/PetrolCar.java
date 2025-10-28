public class PetrolCar extends Car implements Extras {

    @Override
    public double ecoTax(){
        return 200+0.08*engineCapacity;
    }
}
