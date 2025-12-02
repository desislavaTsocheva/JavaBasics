package task4_2;

import task4_1.Book;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientServer implements Runnable {
    private final Socket socket;
    private final Library library;

    public ClientServer(Socket socket, Library library) {
        this.socket = socket;
        this.library = library;
    }

    //трябва ли ни псвм в клиента
    //трябва ли ни клиентската част, ако не къде да напишем този код (Томов каза, че не ни трябва)


    @Override
    public void run() {
        Scanner scanner;
        Scanner scanner1;
        PrintStream printStream;
        try {
            int choice;
            do {
                System.out.println("---------------------MENU----------------------");
                System.out.println("1-> ADD BOOK");
                System.out.println("2-> RENT BOOK");
                System.out.println("3-> RETURN BOOK");
                System.out.println("4-> EXIT");

                //защо имаме 2 scanner-a трябва ли на изпита да ги пишем и 2та
                scanner = new Scanner(System.in);
                scanner1 = new Scanner(socket.getInputStream());

                printStream = new PrintStream(socket.getOutputStream());

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        library.addBook();
                        break;
                    case 2:
                        library.rentBook();
                        break;
                    case 3:
                        library.returnBook();
                        break;
                }
            }
            while (choice != 4);
            printStream.println(" ");
            //за какво се ползва флъша
            printStream.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Client disconnected");
        }
    }
}
