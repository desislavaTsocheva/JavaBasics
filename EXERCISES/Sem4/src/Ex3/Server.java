package Ex3;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.out;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            out.println("Server started");
            while (true) {
                Socket client = serverSocket.accept();
                out.println("Client connected");
                new Thread(() -> run(client)).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run(Socket client) {
        try {
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            Scanner scanner = new Scanner(inputStream);

            outputStream.write("Connection established\n".getBytes());
            outputStream.flush();

            while (true) {
                if (!scanner.hasNextLine()) break;
                String line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    out.write("Exiting Server\n".getBytes());
                    break;
                }

                String[] parts = line.split("\\s+");
                int counter=parts.length;


                if (line.equalsIgnoreCase("exit")) {
                    outputStream.write("Exiting the server\n".getBytes());
                    outputStream.flush();
                    break;
                }

                outputStream.write((counter + "\n").getBytes());
                outputStream.flush();
            }

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
