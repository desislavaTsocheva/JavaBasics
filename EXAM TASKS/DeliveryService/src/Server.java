import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT=5678;

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            BarLogic barLogic = new BarLogic();
            while (true) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Connection accepted");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
                System.out.println("Client on Port " + PORT);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server=new Server();
        server.start();

    }
}
