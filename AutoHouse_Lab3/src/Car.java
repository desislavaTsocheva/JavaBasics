public abstract class Car implements Extras{
    protected String make;
    protected String model;
    protected int year;
    protected double engineCapacity;
    protected double price;

    protected ACType acType;
    protected Interior interior;
    protected RimType rimType;

    public Car(String make, String model, int year, double engineCapacity, double price,
               ACType acType, Interior interior, RimType rimType) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.engineCapacity = engineCapacity;
        this.price = price;
        this.acType = acType;
        this.interior = interior;
        this.rimType = rimType;
    }

    public abstract double ecoTax();

    @Override
    public ACType getACType() { return acType; }
    @Override
    public Interior getInterior() { return interior; }
    @Override
    public RimType getRimType() { return rimType; }

}
