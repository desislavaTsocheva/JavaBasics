import java.time.LocalDate;

public class Reservation {
    private int id;
    private String model;
    private EngineType engineType;
    private GearType gearType;
    private boolean reservation;
    private LocalDate dateFromReservation;
    private LocalDate dateToReservation;

    public Reservation(int id, String model, EngineType engineType, GearType gearType, boolean reservation, LocalDate dateFromReservation, LocalDate dateToReservation) {
        this.id = id;
        this.model = model;
        this.engineType = engineType;
        this.gearType = gearType;
        this.reservation = reservation;
        this.dateFromReservation = dateFromReservation;
        this.dateToReservation = dateToReservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateToReservation() {
        return dateToReservation;
    }

    public void setDateToReservation(LocalDate dateToReservation) {
        this.dateToReservation = dateToReservation;
    }

    public LocalDate getDateFromReservation() {
        return dateFromReservation;
    }

    public void setDateFromReservation(LocalDate dateFromReservation) {
        this.dateFromReservation = dateFromReservation;
    }

    public boolean isReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", model=" + model + ", engineType=" + engineType + ", gearType="+gearType+" IsReservation="+reservation + "From: "+dateFromReservation+"To: "+dateToReservation+" To: "+dateToReservation+"]";
    }
}
