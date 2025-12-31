import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final ReservationLogic reservationLogic;
    public ClientHandler(Socket socket, ReservationLogic reservationLogic) {
        this.socket = socket;
        this.reservationLogic = reservationLogic;
    }
    @Override
    public void run() {
        try (DataInputStream inputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
            int choice;
            do {
                choice = inputStream.readInt();
                switch (choice) {
                    case 1:
                        handleFreeCar(inputStream, outputStream);
                        break;
                    case 2:
                        handleFreeRes(inputStream, outputStream);
                        break;
                    case 3:
                        handlePercent(inputStream, outputStream);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Невалиден избор: " + choice);
                }
            }while (choice != 4);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    private void handleFreeCar(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        int id=inputStream.readInt();
        LocalDate localDate = LocalDate.parse(inputStream.readUTF());
        int res=reservationLogic.returnCar(id, localDate);
        outputStream.writeInt(res);
        outputStream.flush();
    }

    private void handleFreeRes(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        LocalDate localDate = LocalDate.parse(inputStream.readUTF());
        List<Reservation> reservations = reservationLogic.getReservations(localDate);
        for(Reservation reservation : reservations) {
            outputStream.writeInt(reservation.getId());
            outputStream.writeUTF(reservation.getModel());
            outputStream.flush();
        }
    }
    private void handlePercent(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        LocalDate localDate = LocalDate.parse(inputStream.readUTF());
        LocalDate localTime = LocalDate.parse(inputStream.readUTF());
        double percent=reservationLogic.percentNotFree(localDate,localTime);
        outputStream.writeInt((int)percent);
        outputStream.flush();
    }
}
