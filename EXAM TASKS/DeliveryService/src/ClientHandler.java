import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            while (true) {
                String input = in.readLine();
                if (input == null) break;

                int choice = Integer.parseInt(input);

                if (choice == 5) {
                    out.println("Изход от системата.");
                    break;
                }

                switch (choice) {
                    case 1:
                        for (Drink d : BarStorage.getAllDrinks()) {
                            String qty = d.getAmount() > 0
                                    ? String.valueOf(d.getAmount())
                                    : "Няма наличност";
                            out.println(d.getId() + " | " + d.getName() +
                                    " | " + d.getType() + " | " + qty);
                        }
                        out.println("END");
                        break;

                    case 2:
                        int id = Integer.parseInt(in.readLine());
                        int qtyOrder = Integer.parseInt(in.readLine());
                        Drink drink = BarStorage.getDrink(id);

                        if (drink != null) {
                            OrderManager.addOrder(new Order(drink, qtyOrder));
                            out.println("Заявката е подадена успешно.");
                        } else {
                            out.println("Невалидно ID.");
                        }
                        out.println("END");
                        break;

                    case 3:
                        for (Order o : OrderManager.getOrders()) {
                            out.println("Заявка #" + o.getOrderId() +
                                    " | " + o.getDrink().getName() +
                                    " | " + o.getQuantity() +
                                    " | " + o.getStatus());
                        }
                        out.println("END");
                        break;

                    case 4:
                        OrderManager.loadProcessedOrders();
                        out.println("Барът е зареден успешно.");
                        out.println("END");
                        break;

                    default:
                        out.println("Невалидна опция.");
                        out.println("END");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
