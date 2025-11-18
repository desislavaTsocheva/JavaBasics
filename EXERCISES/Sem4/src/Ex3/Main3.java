package Ex3;
import Ex1.Client;
public class Main3 {
    public static void main(String[] args) {
        new Thread(() -> {
            Ex3.Server server = new Server(1000);
            server.start();
        }).start();
        try { Thread.sleep(300); }
        catch (InterruptedException e) {}
        Ex1.Client client = new Client("localhost", 1000);
        client.startClient();
    }
}