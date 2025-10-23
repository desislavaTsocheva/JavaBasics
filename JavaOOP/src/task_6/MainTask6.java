package task_6;

import java.util.ArrayList;
import java.util.List;

public class MainTask6 {
    public static void main(String[] args) {
        List<Drawable> drawables = new ArrayList<>();
        Square square = new Square();
        Triangle triangle = new Triangle();
        drawables.add(square);
        drawables.add(triangle);
        for(Drawable d : drawables) {
            d.draw();
        }
    }
}
