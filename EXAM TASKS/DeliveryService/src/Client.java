import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 5678);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                System.out.println("\n--- МЕНЮ ---");
                System.out.println("1 - Проверка на наличности");
                System.out.println("2 - Подаване на заявка");
                System.out.println("3 - Проверка на заявки");
                System.out.println("4 - Зареждане на бара");
                System.out.println("5 - Изход");
                System.out.print("Избор: ");

                String choice = console.readLine();
                out.println(choice);

                if (choice.equals("5")) {
                    System.out.println(in.readLine());
                    break;
                }

                if (choice.equals("2")) {
                    System.out.print("ID на напитка: ");
                    out.println(console.readLine());

                    System.out.print("Количество: ");
                    out.println(console.readLine());
                }

                String response;
                while (!(response = in.readLine()).equals("END")) {
                    System.out.println(response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
