import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 7777);
            Scanner input = new Scanner(System.in);

            Scanner serverInput = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Connected to server.");

            while (true) {
                System.out.println("---------------MENU------------------");
                System.out.println("1-> Return car");
                System.out.println("2-> List returned cars");
                System.out.println("3-> Rent percent");
                System.out.println("4-> Exit");
                System.out.print("Enter choice: ");

                int choice = input.nextInt();
                out.println(choice);

                if (choice == 4) {
                    System.out.println("Exiting...");
                    break;
                }

                if (serverInput.hasNextLine()) {
                    System.out.println("Server response: " + serverInput.nextLine());
                }
            }

            socket.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
