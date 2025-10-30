package Events;

import Exceptions.NoMoreTicketsException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Concert extends Event{
    protected String starName;

    public Concert(String place, LocalDate date, LocalTime startHour, int numberOfTickets, float priceOfTicket, String starName) {
        super(place, date, startHour, numberOfTickets, priceOfTicket);
        this.starName = starName;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "starName='" + starName + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean sellTickets(int num) throws NoMoreTicketsException {
        if(numberOfTickets<num){
            throw new NoMoreTicketsException("Not enough tickets");
        }
        else{
            numberOfTickets-=num;
            return true;
        }
    }
}
