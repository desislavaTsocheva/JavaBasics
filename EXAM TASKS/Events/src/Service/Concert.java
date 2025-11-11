package Service;

import Exceptions.NoMoreTicketsException;

public class Concert extends Event{
    protected String starName;
    public Concert(String starName, String place, String date, String startHour, int numberOfTickets, double priceOfTicket ){
        super(place,date,startHour,numberOfTickets,priceOfTicket);
        this.starName = starName;
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
