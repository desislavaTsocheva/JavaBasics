package Ex1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private int port;

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
                if (parts.length != 3) {
                    out.write("Invalid command\n".getBytes());
                    continue;
                }

                double a, b;
                try {
                    a = Double.parseDouble(parts[1]);
                    b = Double.parseDouble(parts[2]);
                } catch (Exception e) {
                    out.write("Invalid command\n".getBytes());
                    continue;
                }

                double res;
                switch (parts[0].toUpperCase()) {
                    case "ADD": res = a + b; break;
                    case "SUB": res = a - b; break;
                    case "MUL": res = a * b; break;
                    case "DIV":
                        if (b == 0) {
                            out.write("Division by zero\n".getBytes());
                            continue;
                        }
                        res = a / b;
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
}
