package Ex4;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startClient() {
        try (Socket socket = new Socket(host, port)) {

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            Scanner serverScanner = new Scanner(in);
            Scanner userInput = new Scanner(System.in);

            if (serverScanner.hasNextLine())
                System.out.println(serverScanner.nextLine());

            while (true) {
                System.out.print("> ");
                String command = userInput.nextLine();

                out.write((command + "\n").getBytes());

                if (command.equalsIgnoreCase("EXIT")) {
                    if (serverScanner.hasNextLine())
                        System.out.println(serverScanner.nextLine());
                    break;
                }

                if (serverScanner.hasNextLine())
                    System.out.println(serverScanner.nextLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
