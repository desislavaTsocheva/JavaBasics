import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReservationServer {
    private int PORT;
    private Reservation reservation;

    public ReservationServer(int port, Reservation reservation) {
        this.PORT = port;
        this.reservation = reservation;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                ClientHandler clientHandler = new ClientHandler(socket, reservation);

                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Reservation reservation = new Reservation();
        ReservationServer server = new ReservationServer(7777, reservation);
        server.start();
    }
}
