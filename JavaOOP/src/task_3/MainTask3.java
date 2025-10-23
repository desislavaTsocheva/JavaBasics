package task_3;
import java.util.ArrayList;

public class MainTask3 {
    public static void main(String[] args) {
        ArrayList<Food> foods = new ArrayList<>();

        foods.add(new Apple());
        foods.add(new Bread());
        foods.add(new Cheese());

        for (Food food : foods) {
            food.describe();
            food.eat();
            System.out.println();
        }
    }

}
