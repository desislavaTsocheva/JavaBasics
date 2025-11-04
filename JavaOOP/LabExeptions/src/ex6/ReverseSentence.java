package ex6;

import java.util.Scanner;
import java.util.Stack;

public class ReverseSentence {
    public static void main(String[] args) {
        System.out.println("Enter the string to reverse");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();

        String[] words = s.split("\\s+");

        Stack<String> stack = new Stack<>();
        for (String word : words) {
            stack.push(word);
        }

        System.out.print("Изречението в обратен ред: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
            if (!stack.isEmpty()) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }
}
