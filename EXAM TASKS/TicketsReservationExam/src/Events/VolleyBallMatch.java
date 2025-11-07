package Events;

import Exceptions.NoMoreTicketsException;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class VolleyBallMatch extends Event {
    protected String firstTeam;
    protected String secondTeam;

    public VolleyBallMatch(String place, LocalDate date, LocalTime startHour, int numberOfTickets, float priceOfTicket, String firstTeam, String secondTeam) {
        super(place, date, startHour, numberOfTickets, priceOfTicket);
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    @Override
    public boolean sellTickets(int num) throws NoMoreTicketsException {
        if(numberOfTickets<num){
            throw new NoMoreTicketsException("No enough tickets");
        }
        else{
            numberOfTickets-=num;
            return true;
        }
    }

    @Override
    public String toString() {
        return "Match: " + firstTeam + " vs " + secondTeam + " @ " + place + " (" + date + " " + startHour + ")";
    }
}
