import Events.Concert;
import Events.VolleyBallMatch;

import static Events.EventProcessing.*;

public class Main {
    public static void main(String[] args) {
        processTickets();

        System.out.println("--- CONCERTS ---");
        for (Concert c : concerts) {
            System.out.println(c);
        }

        System.out.println("--- MATCHES ---");
        for (VolleyBallMatch m : matches) {
            System.out.println(m);
        }
    }
}