import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private static final int PORT=6666;
    private final ReservationLogic lock = new ReservationLogic();

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
    private void start() {
        try(ServerSocket serverSocket=new ServerSocket(PORT)){
            while(true){
                Socket client=serverSocket.accept();
                new ClientHandler(client,lock).start();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
