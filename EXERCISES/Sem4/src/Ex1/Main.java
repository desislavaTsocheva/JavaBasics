package Ex1;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            Server server = new Server(1000);
            server.start();
        }).start();
        try { Thread.sleep(300); }
        catch (InterruptedException e) {}
        Client client = new Client("localhost", 1000);
        client.startClient();
    }
}
