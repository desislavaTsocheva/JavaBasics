import java.time.LocalDate;
import java.util.Locale;
import java.util.zip.DataFormatException;

public class Car {
    private String id;
    private String make;
    private String model;
    private String engineType;
    private String gearType;
    private String office;
    private boolean reservation;
    private LocalDate dateFromreservation;
    private LocalDate dateToreservation;

    public Car(String id, String make, String model, String engineType, String gearType, String office, boolean reservation, LocalDate dateFromreservation, LocalDate dateToreservation) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.engineType = engineType;
        this.gearType = gearType;
        this.office = office;
        this.reservation = reservation;
        this.dateFromreservation = dateFromreservation;
        this.dateToreservation = dateToreservation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public LocalDate getDateFromreservation() {
        return dateFromreservation;
    }

    public void setDateFromreservation(LocalDate dateFromreservation) {
        this.dateFromreservation = dateFromreservation;
    }

    public LocalDate getDateToreservation() {
        return dateToreservation;
    }

    public void setDateToreservation(LocalDate dateToreservation) {
        this.dateToreservation = dateToreservation;
    }

    @Override
    public String toString() {
        return id + " " + make + " " + model + " " + engineType + " " + gearType + " office=" + office;
    }
}
