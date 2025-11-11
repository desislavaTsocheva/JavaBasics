package Service;

import Exceptions.NoMoreTicketsException;

public class VolleyballMatch extends Event {
    protected String firstTeam;
    protected String secondTeam;
    public VolleyballMatch(String firstTeam,String secondTeam, String place, String date, String startHour, int numberOfTickets, double priceOfTicket ){
        super(place,date,startHour,numberOfTickets,priceOfTicket);
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }
    @Override
    public boolean sellTicket(int num) throws NoMoreTicketsException {
        try {
            if (num > numberOfTickets) {
                return false;
            }
            else{
                numberOfTickets-=num;
            }
        }
        catch (NoMoreTicketsException e) {
            e.getMessage();
        }
        return true;
    }
}
