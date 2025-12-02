package HotelReservations;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final ConcurrentHashMap<Integer, String> reservations;
    private PrintWriter out;
    private BufferedReader in;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    public ClientHandler(Socket socket, ConcurrentHashMap<Integer, String> reservations) {
        this.socket = socket;
        this.reservations = reservations;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;

            while ((line = in.readLine()) != null) {
                String[] p = line.split("\\s+");
                String cmd = p[0].toUpperCase();
                String response;

                switch (cmd) {
                    case "BOOK": response = book(p); break;
                    case "CANCEL": response = cancel(p); break;
                    case "CHECK": response = check(p); break;
                    case "LIST": response = list(); break;
                    case "FIND": response = find(p); break;
                    case "EXIT": return;
                    default: response = "ERROR: Unknown command";
                }

                out.println(response);
            }
        } catch (IOException ignored) {
        }
    }

    private String book(String[] p) {
        if (p.length != 3) return "ERROR: Syntax BOOK <name> <room>";

        String name = p[1];
        int room;

        if (!NAME_PATTERN.matcher(name).matches())
            return "ERROR: Invalid name";

        try { room = Integer.parseInt(p[2]); }
        catch (Exception e) { return "ERROR: Room must be number"; }

        if (room < 1 || room > 100) return "ERROR: Room must be 1–100";

        synchronized (reservations) {
            if (reservations.containsKey(room))
                return "ERROR: Room already booked";

            reservations.put(room, name);
        }

        return "OK";
    }

    private String cancel(String[] p) {
        if (p.length != 2) return "ERROR: Syntax CANCEL <room>";

        int room;

        try { room = Integer.parseInt(p[1]); }
        catch (Exception e) { return "ERROR: Room must be number"; }

        synchronized (reservations) {
            if (!reservations.containsKey(room))
                return "ERROR: Not booked";

            reservations.remove(room);
        }

        return "OK";
    }

    private String check(String[] p) {
        if (p.length != 2) return "ERROR: Syntax CHECK <room>";

        int room;

        try { room = Integer.parseInt(p[1]); }
        catch (Exception e) { return "ERROR: Room must be number"; }

        return reservations.getOrDefault(room, "FREE");
    }

    private String list() {
        if (reservations.isEmpty()) return "EMPTY";

        StringBuilder sb = new StringBuilder();
        reservations.forEach((room, name) ->
                sb.append(room).append(": ").append(name).append("\n"));

        return sb.toString().trim();
    }

    private String find(String[] p) {
        if (p.length != 2) return "ERROR: Syntax FIND <name>";
        String name = p[1];

        return reservations.entrySet()
                .stream()
                .filter(e -> e.getValue().equalsIgnoreCase(name))
                .map(e -> String.valueOf(e.getKey()))
                .findFirst()
                .orElse("NOT FOUND");
    }
}
