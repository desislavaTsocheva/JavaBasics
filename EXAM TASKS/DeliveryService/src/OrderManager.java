import java.util.*;

public class OrderManager {
    private static List<Order> orders = Collections.synchronizedList(new ArrayList<>());

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static synchronized void loadProcessedOrders() {
        for (Order o : orders) {
            if (o.getStatus().equals("Обработена")) {
                o.getDrink().addQuantity(o.getQuantity());
                o.setStatus("Завършена");
            }
        }
    }
}
