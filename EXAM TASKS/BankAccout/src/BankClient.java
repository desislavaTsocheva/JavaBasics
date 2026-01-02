import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BankClient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 7777;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, PORT);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Свързан към банковия сървър.");

            while (true) {
                System.out.println("\n--- МЕНЮ ---");
                System.out.println("1. Проверка на баланс");
                System.out.println("2. Трансфер на сума");
                System.out.println("3. История на транзакциите");
                System.out.println("4. Изход");
                System.out.print("Избор: ");

                int choice = scanner.nextInt();
                out.writeInt(choice);

                if (choice == 4) break;

                switch (choice) {
                    case 1:
                        System.out.print("Въведете номер на сметка: ");
                        String accNum = scanner.next();
                        out.writeUTF(accNum);

                        double balance = in.readDouble();
                        if (balance < 0) {
                            System.out.println("Грешка: Сметката не съществува.");
                        } else {
                            System.out.println("Текущ баланс: " + balance + " лв.");
                        }
                        break;

                    case 2:
                        System.out.print("От сметка (ID): ");
                        String from = scanner.next();
                        System.out.print("Към сметка (ID): ");
                        String to = scanner.next();
                        System.out.print("Сума за превод: ");
                        double amount = scanner.nextDouble();

                        out.writeUTF(from);
                        out.writeUTF(to);
                        out.writeDouble(amount);

                        String status = in.readUTF();
                        System.out.println("Статус: " + status);
                        break;

                    case 3:
                        System.out.print("Въведете номер на сметка: ");
                        String historyAcc = scanner.next();
                        out.writeUTF(historyAcc);

                        int listSize = in.readInt();
                        if (listSize == 0) {
                            System.out.println("Няма налична история или сметката не съществува.");
                        } else {
                            System.out.println("История на транзакциите:");
                            for (int i = 0; i < listSize; i++) {
                                System.out.println("- " + in.readUTF());
                            }
                        }
                        break;

                    default:
                        System.out.println("Невалиден избор.");
                }
            }
        } catch (IOException e) {
            System.err.println("Грешка при връзката със сървъра: " + e.getMessage());
        }
    }
}