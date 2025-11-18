package Ex2;
import Ex1.Client;
public class Main2 {
    public static void main(String[] args) {
        new Thread(() -> {
            Ex2.Server server = new Server(1000);
            server.start();
        }).start();
        try { Thread.sleep(300); }
        catch (InterruptedException e) {}
        Ex1.Client client = new Client("localhost", 1000);
        client.startClient();
        }
    }