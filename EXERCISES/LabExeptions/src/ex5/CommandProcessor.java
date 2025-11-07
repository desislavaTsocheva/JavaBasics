package ex5;

import java.util.LinkedList;
import java.util.Scanner;

public class CommandProcessor {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> numbers = new LinkedList<>();

        System.out.println("Enter command (add<number>, remove<number>, print, exit): ");

        while (true){
            String input = scanner.nextLine();

            try {
                if(input.isEmpty()) continue;
                processCommand(input, numbers);
            } catch (InvalidCommandException e){
                System.out.println("X" + e.getMessage());
            }
        }
    }

    private static void processCommand(String input, LinkedList<Integer> numbers) throws InvalidCommandException {
        String[] parts = input.split(" ");
        String command = parts[0];

        switch (command){
            case "add":
                if(parts.length != 2) throw new InvalidCommandException("Invalid command!!!");
                try {
                    int numberToAdd = Integer.parseInt(parts[1]);
                    numbers.add(numberToAdd);
                    System.out.println("Added number: " + numberToAdd);
                } catch (NumberFormatException e){
                    throw new InvalidCommandException("Invalid command!!!");
                }
                break;
            case "remove":
                if(parts.length != 2) throw new InvalidCommandException("Invalid command!!!");
                try {
                    int numberToRemove = Integer.parseInt(parts[1]);
                    if (numbers.remove((Integer) numberToRemove)){
                        System.out.println("Removed number: " + numberToRemove);
                    } else {
                        System.out.println("There is no such number in the list.");
                    }
                } catch (NumberFormatException e){
                    throw new InvalidCommandException("Invalid command!!!");
                }
                break;
            case "print":
                if(numbers.isEmpty()){
                    System.out.println("The list is empty.");
                } else {
                    System.out.println("List: " + numbers);
                }
                break;
            case "exit":
                System.out.println("The program ended.");
                System.exit(0);
                break;
            default:
                throw new InvalidCommandException("Invalid command!!!");
        }
    }
}
