package BankSystem;

import java.io.*;
import java.net.Socket;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final ConcurrentHashMap<String, Integer> accounts;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket,
                             ConcurrentHashMap<String, Integer> accounts) {
        this.socket = socket;
        this.accounts = accounts;
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
                    case "CREATE": response = create(p); break;
                    case "DEPOSIT": response = deposit(p); break;
                    case "WITHDRAW": response = withdraw(p); break;
                    case "BALANCE": response = balance(p); break;
                    case "TRANSFER": response = transfer(p); break;
                    case "TOP3": response = top3(); break;
                    case "EXIT": return;
                    default: response = "ERROR: Unknown command";
                }

                out.println(response);
            }
        } catch (IOException ignored) {}
    }

    private String create(String[] p) {
        if (p.length != 2) return "ERROR: Syntax CREATE <username>";

        String user = p[1];

        if (accounts.putIfAbsent(user, 0) != null)
            return "ERROR: User exists";

        return "OK";
    }

    private String deposit(String[] p) {
        if (p.length != 3) return "ERROR: Syntax DEPOSIT <username> <amount>";

        String user = p[1];
        int amount;

        try { amount = Integer.parseInt(p[2]); }
        catch (Exception e) { return "ERROR: amount must be number"; }

        if (amount <= 0) return "ERROR: amount > 0";

        accounts.compute(user, (u, balance) -> {
            if (balance == null) return null;
            return balance + amount;
        });

        if (!accounts.containsKey(user)) return "ERROR: User not found";

        return "OK";
    }

    private String withdraw(String[] p) {
        if (p.length != 3) return "ERROR: Syntax WITHDRAW <username> <amount>";

        String user = p[1];
        int amount;

        try { amount = Integer.parseInt(p[2]); }
        catch (Exception e) { return "ERROR: amount must be number"; }

        if (amount <= 0) return "ERROR: amount > 0";

        synchronized (accounts) {
            if (!accounts.containsKey(user))
                return "ERROR: User not found";

            int bal = accounts.get(user);
            if (bal < amount)
                return "ERROR: Insufficient funds";

            accounts.put(user, bal - amount);
        }

        return "OK";
    }

    private String balance(String[] p) {
        if (p.length != 2) return "ERROR: Syntax BALANCE <username>";

        String user = p[1];

        Integer bal = accounts.get(user);
        if (bal == null) return "ERROR: User not found";

        return bal.toString();
    }

    private String transfer(String[] p) {
        if (p.length != 4) return "ERROR: Syntax TRANSFER <from> <to> <amount>";

        String from = p[1];
        String to = p[2];
        int amount;

        try { amount = Integer.parseInt(p[3]); }
        catch (Exception e) { return "ERROR: amount must be number"; }

        if (amount <= 0) return "ERROR: amount > 0";

        synchronized (accounts) {
            if (!accounts.containsKey(from) || !accounts.containsKey(to))
                return "ERROR: User not found";

            int balFrom = accounts.get(from);

            if (balFrom < amount)
                return "ERROR: Insufficient funds";

            accounts.put(from, balFrom - amount);
            accounts.put(to, accounts.get(to) + amount);
        }

        return "OK";
    }

    private String top3() {
        return accounts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .map(e -> e.getKey() + ": " + e.getValue())
                .reduce((a, b) -> a + "\n" + b)
                .orElse("EMPTY");
    }
}
