import java.net.ServerSocket;
import java.net.Socket;
import java.security.PublicKey;

public class Server {
    private static final int PORT=5678;
    private AutoPartsService autoPartsService;
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
    public void start()
    {
        try{
            ServerSocket serverSocket = new ServerSocket(PORT);
            while(true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket,autoPartsService);
                handler.start();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
