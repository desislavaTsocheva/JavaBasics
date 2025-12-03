import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Reservation reservation;
    private Socket socket;

    public ClientHandler(Socket socket, Reservation reservation) {
        this.reservation = reservation;
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner sc = null;
        try {
            sc = new Scanner(socket.getInputStream());

            while (true) {
                System.out.println("---------------MENU------------------");
                System.out.println("1-> Return car");
                System.out.println("2-> List returned cars");
                System.out.println("3-> Rent percent");
                System.out.println("4-> Exit");

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: reservation.returnCar(); break;
                    case 2: reservation.findCars(); break;
                    case 3: reservation.rentPercent(); break;
                    case 4:
                        socket.close();
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }

        } catch (Exception e) {
            System.out.println("Client disconnected.");
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
            if (sc != null) sc.close();
        }
    }

}
