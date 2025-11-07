package task_3;

public class Apple extends Food {
    @Override
    public void describe() {
        System.out.println("A sweet fruit");
    }

    @Override
    public void eat() {
        System.out.println("You eat the apple");
    }
}
