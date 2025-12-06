import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReservationServer {

    private final int PORT;
    public ReservationServer(int PORT) {
        this.PORT = PORT;
    }

    public void start() {
        Reservation res = new Reservation();
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, res);
                new Thread(clientHandler).start();
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 7777;
        ReservationServer server = new ReservationServer(port);
        server.start();
    }
}