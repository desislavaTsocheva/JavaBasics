import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Dealership{
    ArrayList<Car> cars=new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public double ecoTaxSum(){
        double sum=0;
        for(Car car:cars){
            sum+=car.ecoTax();
        }
        return sum;
    }

    public List<Car> filterByExtra(String category, String value) {
        String cat = category.toUpperCase();
        String val = value.toUpperCase();
        List<Car> result = new ArrayList<>();

        for (Car car : cars) {
            if (cat.equals("AC") && car.getACType().name().equals(val)) {
                result.add(car);
            } else if (cat.equals("INTERIOR") && car.getInterior().name().equals(val)) {
                result.add(car);
            } else if (cat.equals("RIMS") && car.getRimType().name().equals(val)) {
                result.add(car);
            }
        }
        return result;
    }


    public void listAllCars() {
        for (Car car : cars) {
            System.out.print(car.make+" "+car.model+" "+car.year+" "+car.engineCapacity+" "+car.price+"\n");
        }

    }
}
