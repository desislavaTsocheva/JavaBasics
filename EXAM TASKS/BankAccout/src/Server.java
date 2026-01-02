import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server{
    private static final int PORT = 7777;
    static final Map<String, Account> accounts = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        accounts.put("BG01", new Account("BG01", "Иван Иванов", 1000.0));
        accounts.put("BG02", new Account("BG02", "Мария Петрова", 500.0));

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Банковият сървър стартира на порт " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
