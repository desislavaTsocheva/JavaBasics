public class AutoParts {
    private final int id;
    private String name;
    private Category category;
    private int amount;

    public AutoParts(Category category, int id, String name, int amount) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("AutoParts: id-> %d ; name-> %s ; amount-> %d ; category-> %s", id, name, amount, category);
    }
}
