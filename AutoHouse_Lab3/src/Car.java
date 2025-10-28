public abstract class Car implements Extras{
    protected String make;
    protected String model;
    protected int year;
    protected double engineCapacity;
    protected double price;

    public abstract double ecoTax();
}
