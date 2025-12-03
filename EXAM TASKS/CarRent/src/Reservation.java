import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reservation {
    private ArrayList<Car> reservations = new ArrayList<Car>();

    public String returnCar() throws IOException {
        String reservationId;
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the car ID: ");
        reservationId = scan.readLine();

        if(!findId(reservationId)) {
            System.out.println("No such car ID");
        }

        String office;
        System.out.println("Enter the office name: ");
        office = scan.readLine();

        if(office.equalsIgnoreCase("")) {
            System.out.println("Office name cannot be empty ");
        }

        LocalDate date;
        System.out.println("Enter the date: ");
        date = LocalDate.parse(scan.readLine());
        if(!validDate(date)) {
            System.out.println("Invalid date");
        }

        for(Car car : reservations) {
            if(car.getId().equalsIgnoreCase(reservationId)) {
                car.setReservation(false);
                car.setOffice(office);
                car.setDateToreservation(null);
                car.setDateFromreservation(null);
                return reservationId;
            }
            else{
                return null;
            }
        }
        return null;
    }

    public ArrayList<Car> findCars() throws IOException {
        ArrayList<Car> cars = new ArrayList<>();
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        String office;
        System.out.println("Enter the office name: ");
        office = scan.readLine();

        if (office.equalsIgnoreCase("")) {
            System.out.println("Office name cannot be empty ");
        }

        LocalDate date;
        System.out.println("Enter the date: ");
        date = LocalDate.parse(scan.readLine());
        if (!validDate(date)) {
            System.out.println("Invalid date");
        }

        for (Car car : reservations) {
            if (car.getOffice().equalsIgnoreCase(office)) {
                if (validDate(date)) {
                    cars.add(car);
                }
            }
        }
        if (cars.isEmpty()) {
            System.out.println("No such car");
            return null;
        } else {
            return cars;
        }
    }

    public int rentPercent() throws IOException {
        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        LocalDate date;
        System.out.println("Enter the date: ");
        date = LocalDate.parse(scan.readLine());
        if (!validDate(date)) {
            System.out.println("Invalid date");
        }

        int counterFree=0;
        for(Car car : reservations) {
            if (validDate(date)) {
                counterFree++;
            }
        }
        int percent=(counterFree/reservations.size())*100;
        if(counterFree==0){
            return 0;
        }
        else {
            return percent;
        }
    }

    public boolean validDate(LocalDate date) {
        for(Car car : reservations) {
            if(date.isBefore(car.getDateFromreservation())||date.isAfter(car.getDateToreservation())) {
                return false;
            }
        }
        return true;
    }

    public boolean findId(String reservationId) throws IOException {
        for(Car car : reservations) {
            if(car.getId().equalsIgnoreCase(reservationId)) {
                return true;
            }
        }
        return false;
    }
}
