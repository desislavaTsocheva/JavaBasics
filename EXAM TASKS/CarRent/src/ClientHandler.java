import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
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
        Scanner serverSc = null;
        PrintStream out = null;
        int choice;
        try {
            serverSc = new Scanner(System.in);
            out = new PrintStream(socket.getOutputStream());

            do {
                System.out.println("---------------MENU------------------");
                System.out.println("1-> Return car");
                System.out.println("2-> List returned cars");
                System.out.println("3-> Rent percent");
                System.out.println("4-> Exit");
                System.out.println("Enter choice: ");

                choice = serverSc.nextInt();
                serverSc.nextLine();

                switch (choice) {
                    case 1:
                        reservation.returnCar();
                        break;
                    case 2:
                        System.out.println(reservation.findCars());
                        break;
                    case 3:
                        System.out.println(reservation.rentPercent());
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
                out.println();
                out.flush();
            } while(choice != 4);

        } catch (Exception e) {
            System.err.println("Server ClientHandler Error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket closing error: " + e.getMessage());
            }
            System.out.println("Client disconnected");
        }
    }
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7777);
        Reservation reservation = new Reservation();
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("A1", "Toyota", "Corolla", "Petrol", "Automatic",
                "Sofia", false,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 10)));
        reservation.setReservations(cars);
        ClientHandler client = new ClientHandler(socket, reservation);
        //client.run();
    }
}
