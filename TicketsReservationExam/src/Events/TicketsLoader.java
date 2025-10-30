package Events;

import Exceptions.UnrecognisedRowException;
import Files.Importable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TicketsLoader implements Importable {

    private static final String FILE_NAME = "C:\\Users\\ASUS\\Desktop\\TicketsReservationExam\\daily_tickets.txt";

    @Override
    public Object[] importDataFromFile() throws IOException
    {
        System.out.println("Опит за четене от: " + FILE_NAME);
        if(!new File(FILE_NAME).exists()){
            System.out.println("no such file: " + FILE_NAME);
        }
        ArrayList<Event> events = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Event event = parseLine(line);
                    if (event != null) {
                        events.add(event);
                    }
                } catch (UnrecognisedRowException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
            return events.toArray(new Event[0]);
    }
    private Event parseLine(String line) throws UnrecognisedRowException {
        Event event = null;
        System.out.println("Четене на ред: " + line);
        String[] parts = line.split("\\*");
        try {

            int type = Integer.parseInt(parts[0].trim());

            if (type == 1) {
                String performer = parts[1].trim();
                String place = parts[2].trim();
                LocalDate date = LocalDate.parse(parts[3].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                LocalTime time = LocalTime.parse(parts[4].trim());
                int capacity = Integer.parseInt(parts[5].trim());
                float ticketPrice = (float) Double.parseDouble(parts[6].trim());

                event = new Concert(place, date, time, capacity, ticketPrice, performer);
                System.out.println("Concert: " + event);
                return event;
            } else if (type == 2) {
                String team1 = parts[1].trim();
                String team2 = parts[2].trim();
                String venue = parts[3].trim();
                LocalDate date = LocalDate.parse(parts[4].trim(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                LocalTime time = LocalTime.parse(parts[5].trim());
                int capacity = Integer.parseInt(parts[6].trim());
                float ticketPrice = (float) Double.parseDouble(parts[7].trim());
                System.out.println("Match: " + event);
                event = new VolleyBallMatch(venue, date, time, capacity, ticketPrice, team1, team2);
                return event;
            } else {
                throw new UnrecognisedRowException("Unknown event type: " + type);
            }
        } catch (Exception e) {
            throw new UnrecognisedRowException("Line " + line + " is invalid");
        }
    }
}
