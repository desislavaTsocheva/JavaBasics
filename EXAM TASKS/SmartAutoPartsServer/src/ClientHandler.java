import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToIntFunction;

public class ClientHandler extends Thread {

    private Socket socket;
    private AutoPartsService autoPartsService;

    public ClientHandler(Socket socket, AutoPartsService autoPartsService) {
        this.socket = socket;
        this.autoPartsService = autoPartsService;
    }

    public void start() {
        System.out.println("Client connected: " + socket.getRemoteSocketAddress());
        try (Socket s = socket;
             DataInputStream in = new DataInputStream(s.getInputStream());
             DataOutputStream out = new DataOutputStream(s.getOutputStream())) {

            while (true) {
                int command;
                try {
                    command = in.readInt();
                } catch (EOFException eof) {
                    break;
                }

                if (command == 1) {
                    handleMakeRequest(in, out);
                } else if (command == 2) {
                    handleRequest(in, out);
                } else if (command == 3) {
                    handleListByStatus(in, out);
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error with client " + socket.getRemoteSocketAddress()
                    + ": " + e.getMessage());
        } finally {
            System.out.println("Client disconnected: " + socket.getRemoteSocketAddress());
        }
    }
    public void handleMakeRequest(DataInputStream in, DataOutputStream out) throws IOException {
        List<AutoParts> autoParts=autoPartsService.getAutoParts();
        for(AutoParts autoPart:autoParts){
            out.writeInt(autoPart.getId());
            out.writeUTF(autoPart.getName());
            out.writeInt(autoPart.getAmount());
        }
        out.flush();
    }

    public void handleRequest(DataInputStream in, DataOutputStream out) throws IOException {
        int id = in.readInt();
        int amount = in.readInt();

    }
    public void handleListByStatus(DataInputStream in, DataOutputStream out) throws IOException {

    }
}
