package task_3;

public class Cheese extends Food {
    @Override
    public void describe() {
        System.out.println("Made from milk");
    }
    @Override
    public void eat() {
        System.out.println("You eat the cheese");
    }
}
