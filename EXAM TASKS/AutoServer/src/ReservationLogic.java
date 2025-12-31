import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationLogic {
    List<Reservation> reservations = new ArrayList<Reservation>();

    public ReservationLogic() {
        reservations.add(new Reservation(
                101,
                "BMW X5",
                EngineType.Diesel,
                GearType.Automatic,
                true,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 30)
        ));
    }


    public synchronized Integer returnCar(int id, LocalDate date) {
        for (Reservation reservation : reservations) {
            if(reservation.getId() == id&&reservation.getDateToReservation().isBefore(date)) {
                reservation.setReservation(false);
                reservation.setDateFromReservation(null);
                reservation.setDateToReservation(null);
                return reservation.getId();
            }
        }
        return null;
    }

    public synchronized List<Reservation> getReservations(LocalDate date) {
        List<Reservation> freeCars=new ArrayList<>();
        for (Reservation reservation : reservations) {
            if(reservation.getDateToReservation().isBefore(date)) {
                freeCars.add(reservation);
            }
        }
        return freeCars;
    }

    public synchronized double percentNotFree(LocalDate dateFrom, LocalDate dateTo) {
        int counter=0;
        int size=reservations.size();
        double percent;
        for (Reservation reservation : reservations) {
            if(reservation.getDateFromReservation().isAfter(dateFrom) && reservation.getDateToReservation().isBefore(dateTo)) {
                counter++;
            }
        }
        percent = (double)counter/size;
        return percent;
    }
}
