package Ex4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
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

                String res;
                switch (parts[0].toUpperCase()) {
                    case "TIME":
                        String timeStr = java.time.LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        out.write((timeStr + "\n").getBytes());
                        continue;

                    case "NAME":
                        String name=parts[1].toString();
                        res = ("Hello, "+name); break;
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
