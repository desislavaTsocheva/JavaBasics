package Ex5;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private int port;
    private double balance=0;
    public Server(int port) {
        this.port = port;
    }

    public void start(){
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connected!");

                new Thread(() -> run(client)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    public void run(Socket socket) {
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Scanner sc = new Scanner(in);

            out.write("Connection Established\n".getBytes());

            while (true) {
                if (!sc.hasNextLine()) break;

                String line = sc.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    out.write("Exiting Server\n".getBytes());
                    break;
                }

                String[] parts = line.split("\\s+");
                double res;
                double newBalance;
                switch (parts[0].toUpperCase()) {
                    case "DEPOSIT":
                        if(parts.length != 2) {
                            out.write("Invalid command\n".getBytes());
                            continue;
                        }

                        double deposit=Double.parseDouble(parts[1]);
                        newBalance=deposit(deposit);
                        res = newBalance;
                        break;
                    case "WITHDRAW":
                        if(parts.length != 2) {
                            out.write("Invalid command\n".getBytes());
                            continue;
                        }

                        double withdraw=Double.parseDouble(parts[1]);
                        if(withdraw>balance) {
                            out.write("Invalid command (you dont have enough money)\n".getBytes());
                        }
                        else {
                            newBalance = withdraw(withdraw);
                            res = newBalance;break;
                        }
                    case "BALANCE":
                        double currentBalance=getBalance();
                        res=currentBalance;
                        break;
                    default:
                        out.write("Invalid command\n".getBytes());
                        continue;
                }
                out.write((res + "\n").getBytes());
            }
            socket.close();
        } catch (Exception e) {
            System.out.println("Client disconnected");
        }
    }
    private synchronized double deposit(double amount) {
        balance += amount;
        return balance;
    }

    private synchronized double withdraw(double amount) {
        balance -= amount;
        return balance;
    }

    private synchronized double getBalance() {
        return balance;
    }
}
