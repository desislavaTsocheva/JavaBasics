package Warehouse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final ConcurrentHashMap<String, Integer> warehouse;
    private PrintWriter out;
    private BufferedReader in;

    private static final Pattern PRODUCT_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    public ClientHandler(Socket clientSocket, ConcurrentHashMap<String, Integer> warehouse) {
        this.clientSocket = clientSocket;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);

                if (inputLine.equalsIgnoreCase("exit")) {
                    System.out.println("Client thread stopped.");
                    break;
                }

                String[] parts = inputLine.split("\\s+");
                String command = parts[0].toUpperCase();

                String response;

                switch (command) {
                    case "ADD": response = add(parts); break;
                    case "REMOVE": response = remove(parts); break;
                    case "SET": response = set(parts); break;
                    case "GET": response = get(parts); break;
                    case "LIST": response = list(); break;
                    default: response = "ERROR: Unknown command";
                }

                out.println(response);   // <-- ТОВА Е КРИТИЧНО
            }

        } catch (IOException e) {
            System.out.println("Client disconnected: " + e.getMessage());
        } finally {
            try { clientSocket.close(); } catch (IOException ignored) {}
        }
    }


    private boolean validProduct(String product) {
        return PRODUCT_PATTERN.matcher(product).matches();
    }

    private String add(String[] p) {
        if (p.length != 3) return "ERROR: Syntax ADD <product> <count>";

        String product = p[1];
        if (!validProduct(product)) return "ERROR: Invalid product name";

        int count;
        try { count = Integer.parseInt(p[2]); }
        catch (NumberFormatException e) { return "ERROR: count must be integer"; }

        if (count <= 0) return "ERROR: Count must be > 0";

        warehouse.merge(product, count, Integer::sum);

        System.out.println("[LOG] ADD " + product + " + " + count);
        return "OK";
    }

    private String remove(String[] p) {
        if (p.length != 3) return "ERROR: Syntax REMOVE <product> <count>";

        String product = p[1];
        if (!warehouse.containsKey(product)) return "ERROR: Product not found";

        int count;
        try { count = Integer.parseInt(p[2]); }
        catch (NumberFormatException e) { return "ERROR: count must be integer"; }

        int current = warehouse.get(product);
        if (count > current) return "ERROR: Insufficient quantity";

        warehouse.put(product, current - count);

        System.out.println("[LOG] REMOVE " + product + " - " + count);
        return "OK";
    }

    private String set(String[] p) {
        if (p.length != 3) return "ERROR: Syntax SET <product> <count>";

        String product = p[1];
        if (!validProduct(product)) return "ERROR: Invalid product name";

        int newValue;
        try { newValue = Integer.parseInt(p[2]); }
        catch (NumberFormatException e) { return "ERROR: count must be integer"; }

        if (newValue < 0) return "ERROR: Count must be >= 0";

        warehouse.put(product, newValue);

        System.out.println("[LOG] SET " + product + " = " + newValue);
        return "OK";
    }

    private String get(String[] p) {
        if (p.length != 2) return "ERROR: Syntax GET <product>";

        String product = p[1];
        if (!warehouse.containsKey(product)) return "ERROR: Product not found";

        return warehouse.get(product).toString();
    }

    private String list() {
        if (warehouse.isEmpty()) return "EMPTY";

        StringBuilder sb = new StringBuilder();
        warehouse.forEach((k, v) -> sb.append(k).append(": ").append(v).append(", "));

        return sb.substring(0, sb.length() - 2);
    }
}
