package Events;

import Exceptions.NoMoreTicketsException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public abstract class Event {
    protected String place;
    protected LocalDate date;
    protected LocalTime startHour;
    protected int numberOfTickets;
    protected float priceOfTicket;

    public Event(String place, LocalDate date, LocalTime startHour, int numberOfTickets, float priceOfTicket) {
        this.place = place;
        this.date = date;
        this.startHour = startHour;
        this.numberOfTickets = numberOfTickets;
        this.priceOfTicket = priceOfTicket;
    }

    abstract boolean sellTickets(int num) throws NoMoreTicketsException;
}
