import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RentACarClient {
    private static final String HOST = "localhost";
    private static final int PORT = 6666;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Изберете опция (1: Връщане, 2: Свободни коли, 3: Справка %): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try (Socket socket = new Socket(HOST, PORT);
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 DataInputStream in = new DataInputStream(socket.getInputStream())) {

                out.writeInt(choice);
do {
    switch (choice) {
        case 1:
            System.out.print("Въведете ID на резервация: ");
            int resId = scanner.nextInt();
            System.out.print("Въведете дата (ГГГГ-ММ-ДД): ");
            String dateStr = scanner.next();

            out.writeInt(resId);
            out.writeUTF(dateStr);
            out.flush();

            int result = in.readInt();
            System.out.println("Резултат от сървъра: " + result);
            break;

        case 2:
            System.out.print("Въведете дата за търсене (ГГГГ-ММ-ДД): ");
            String searchDate = scanner.next();

            out.writeUTF(searchDate);
            out.flush();
            System.out.println("Свободни резервации:");
            try {
                while (true) {
                    int id = in.readInt();
                    String model = in.readUTF();
                    System.out.println("ID: " + id + ", Модел: " + model);
                }
            } catch (EOFException e) {
                System.out.println("--- Край на списъка ---");
            }
            break;

        case 3:
            System.out.print("Въведете дата (ГГГГ-ММ-ДД): ");
            String d1 = scanner.next();
            System.out.print("Въведете втора дата (ГГГГ-ММ-ДД): ");
            String d2 = scanner.next();

            out.writeUTF(d1);
            out.writeUTF(d2);
            out.flush();

            int percent = in.readInt();
            System.out.println("Заети автомобили: " + percent + "%");
            break;
    }
}while (choice != 4);
            }
        } catch (IOException e) {
            System.err.println("Грешка при връзка със сървъра: " + e.getMessage());
        }
    }
}