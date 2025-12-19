public class Order {
    private static int counter = 1;

    private int orderId;
    private Drink drink;
    private int quantity;
    private String status; // Подадена, Обработва се, Обработена, Завършена

    public Order(Drink drink, int quantity) {
        this.orderId = counter++;
        this.drink = drink;
        this.quantity = quantity;
        this.status = "Подадена";
    }

    public synchronized String getStatus() {
        return status;
    }

    public synchronized void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() { return orderId; }
    public Drink getDrink() { return drink; }
    public int getQuantity() { return quantity; }
}
