import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    private final Socket socket;
    public ClientHandler(Socket socket) { this.socket = socket; }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            while (true) {
                try {
                    int code = in.readInt();
                    switch (code) {
                        case 1:
                            handleBalance(in, out);
                            break;
                        case 2:
                            handleTransfer(in, out);
                            break;
                        case 3:
                            handleHistory(in, out);
                            break;
                        default:
                            out.writeUTF("Невалиден код.");
                    }
                } catch (EOFException e) { break; } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.err.println("Клиентът прекъсна връзката.");
        }
    }

    private void handleBalance(DataInputStream in, DataOutputStream out) throws IOException {
        String accNum = in.readUTF();
        Account acc = Server.accounts.get(accNum);
        if (acc != null) {
            out.writeDouble(acc.getBalance());
        } else {
            out.writeDouble(-1.0);
        }
    }

    private void handleTransfer(DataInputStream in, DataOutputStream out) throws IOException {
        String fromId = in.readUTF();
        String toId = in.readUTF();
        double amount = in.readDouble();

        Account from = Server.accounts.get(fromId);
        Account to = Server.accounts.get(toId);

        if (from == null || to == null) {
            out.writeUTF("ГРЕШКА: Невалидна сметка.");
            return;
        }

        Account first = fromId.compareTo(toId) < 0 ? from : to;
        Account second = fromId.compareTo(toId) < 0 ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    out.writeUTF("УСПЕХ: Преводът е извършен.");
                } else {
                    out.writeUTF("ГРЕШКА: Недостатъчна наличност.");
                }
            }
        }
    }

    private void handleHistory(DataInputStream in, DataOutputStream out) throws IOException {
        String accNum = in.readUTF();
        Account acc = Server.accounts.get(accNum);
        if (acc != null) {
            List<String> history = acc.getHistory();
            out.writeInt(history.size());
            for (String event : history) {
                out.writeUTF(event);
            }
        } else {
            out.writeInt(0);
        }
    }
}