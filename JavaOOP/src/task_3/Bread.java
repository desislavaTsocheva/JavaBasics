package task_3;

public class Bread extends Food{
    @Override
    public void describe() {
        System.out.println("Baked from flour");
    }
    @Override
    public void eat() {
        System.out.println("You eat the bread");
    }
}
