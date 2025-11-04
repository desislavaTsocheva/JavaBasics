package ex3;

import java.util.Scanner;

public class DivisionProgram {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.println("Enter first number: ");
            double a = scanner.nextDouble();
            System.out.println("Enter second number: ");
            double b = scanner.nextDouble();

            double result = calculator.divide(a, b);
            System.out.println("Result: " + result);
        } catch (DivisionByZeroException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Invalid entry!");
        }
    }
}
