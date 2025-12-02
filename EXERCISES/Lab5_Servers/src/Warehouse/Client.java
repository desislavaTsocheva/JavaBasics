package Warehouse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 12345;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to warehouse server.");
            System.out.println("Available commands: ADD, REMOVE, SET, GET, LIST");
            System.out.println("Type 'exit' to close client.\n");

            while (true) {
                String command = scanner.nextLine().trim();

                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Closing client...");
                    break;
                }

                out.println(command);
                String response = in.readLine();

                if (response == null) {
                    System.out.println("Server closed connection.");
                    break;
                }

                System.out.println(response);
            }

        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }
}
