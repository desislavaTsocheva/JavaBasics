package CashLogic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket=new ServerSocket(8080);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        while (true) {
            Socket client = socket.accept();
            executor.execute(new ServerThread(client));
        }
    }
}
