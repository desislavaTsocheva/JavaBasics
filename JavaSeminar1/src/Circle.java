import java.security.PublicKey;
import java.util.Scanner;

public class Circle {

    public static final double PI=3.14159;
    public static double circleArea(double radius) {
        return PI*radius*radius;
    }
    public static double circlePerimeter(double radius) {
        return PI*2*radius;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the radius of the circle: ");
        double radius = sc.nextDouble();
        System.out.println("The circle area of the circle is " + Math.round(circleArea(radius)));
        System.out.println("The perimeter of the circle is " + Math.round(circlePerimeter(radius)));
    }
}
