package HotelReservations;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 12345;
    private final ConcurrentHashMap<Integer, String> reservations = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        new Server().startServer();
    }

    public void startServer() {
        System.out.println("Server on port: " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client on port : " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, reservations);

                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println( e.getMessage());
        }
    }
}