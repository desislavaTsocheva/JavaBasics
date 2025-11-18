package Ex2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.System.out;

public class Server {
    private int port;
    public Server(int port) {
        this.port = port;
    }

    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            out.println("Server started");
            while(true){
                Socket client=serverSocket.accept();
                out.println("Client connected");
                new Thread(()->run(client)).start();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void run(Socket client) {
        try {
            InputStream inputStream = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            Scanner scanner = new Scanner(inputStream);

            outputStream.write("Connection established\n".getBytes());
            outputStream.flush();

            while (true) {
                if (!scanner.hasNextLine()) break;

                String line = scanner.nextLine();
                line = line.trim();

                if (line.equalsIgnoreCase("exit")) {
                    outputStream.write("Exiting the server\n".getBytes());
                    outputStream.flush();
                    break;
                }

                String[] parts = line.split("\\s+");

                String word = parts[0];
                String reversed = new StringBuilder(word).reverse().toString();

                outputStream.write((reversed + "\n").getBytes());
                outputStream.flush();
            }

            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
