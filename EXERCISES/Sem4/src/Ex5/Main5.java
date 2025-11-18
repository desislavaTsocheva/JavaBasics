package Ex5;
import Ex5.Client;

public class Main5 {
    public static void main(String[] args) {
        new Thread(() -> {
            Ex5.Server server = new Server(1000);
            server.start();
        }).start();
        try { Thread.sleep(300); }
        catch (InterruptedException e) {}
        Ex5.Client client = new Client("localhost", 1000);
        client.startClient();
    }
}