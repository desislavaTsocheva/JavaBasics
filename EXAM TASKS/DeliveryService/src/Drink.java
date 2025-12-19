public class Drink {
    private int id;
    private String name;
    private Types type;
    private int amount;

    public Drink(int id, String name, int amount,Types type) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public synchronized void addQuantity(int q) {
        amount += q;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    @Override
    public String toString() {
        if(amount==0){
            String qtyText = (amount > 0) ? String.valueOf(amount) : "НЯМА В НАЛИЧНОСТ";
        }
        return "Drink id: " + id + " name: " + name + " amount: " + amount+ "type: " +type.name();
    }
}
