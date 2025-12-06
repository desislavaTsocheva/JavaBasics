package TouristAgency;

public class TouristOffer {
    private String name;
    private String address;
    private String destination;
    private double price;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TouristOffer(String name, String address, String destination, double price) {
        this.name = name;
        this.address = address;
        this.destination = destination;
        this.price = price;
    }
}
