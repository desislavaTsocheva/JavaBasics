package Events;

public class EventProcessing {
    public static Concert[] concerts;
    public static VolleyBallMatch[] matches;

    public static void processTickets(){
        TicketsLoader ticketsLoader = new TicketsLoader();
        try{
            Object[] data=ticketsLoader.importDataFromFile();
            int concertCount=0;
            int matchesCount=0;
            for(Object ticket:data){
                if(ticket instanceof Concert){
                    concertCount++;
                }
                else{
                    matchesCount++;
                }
            }
            concerts = new Concert[concertCount];
            matches = new VolleyBallMatch[matchesCount];

            int c = 0, v = 0;
            for (Object ticket : data) {
                if (ticket instanceof Concert) {
                    concerts[c++] = (Concert) ticket;
                } else if (ticket instanceof VolleyBallMatch) {
                    matches[v++] = (VolleyBallMatch) ticket;
                }
            }

            System.out.println("Concerts count is: " + concertCount + "\nMatches count is: " + matchesCount);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
