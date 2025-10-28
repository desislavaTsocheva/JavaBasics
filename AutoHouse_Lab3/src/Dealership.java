import java.sql.Array;
import java.util.*;

public class Dealership{
    ArrayList<Car> cars=new ArrayList<>();
    public double ecoTaxSum(){
        double sum=0;
        for(Car car:cars){
            sum+=car.ecoTax();
        }
        return sum;
    }
    public ArrayList<Car> filterCars(Extras.ACType acType, Extras.Interior interior,Extras.RimType rimType,ArrayList<Car> cars){
        ArrayList<Car> filteredCars=new ArrayList<Car>();
        for(Car car:cars){
            if(acType.toString().equals(car.getACManual())){
                filteredCars.add(car);
                return filteredCars;
            }
            else if(acType.toString().equals(car.getACAutomatic())){
                filteredCars.add(car);
                return filteredCars;
            }
            else if(interior.toString().equals(car.getInteriorFabric())){
                filteredCars.add(car);
                return filteredCars;
            }
            else if(interior.toString().equals(car.getInteriorLeather())){
                filteredCars.add(car);
                return filteredCars;
            }
            else if(rimType.toString().equals(car.getRimTypeAlloy())){
                filteredCars.add(car);
                return filteredCars;
            }
            else if(rimType.toString().equals(car.getRimTypeSteel())){
                filteredCars.add(car);
                return filteredCars;
            }
        }
        return filteredCars;
    }
}
