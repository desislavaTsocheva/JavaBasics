package ex9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MessageManager {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        List<String> messages = new ArrayList<>();

        System.out.println("Enter command: ");
        System.out.println("Add <text>: ");
        System.out.println("Show: ");
        System.out.println("Undo: ");
        System.out.println("Exit: ");

        while(true){
            System.out.println("\n> ");
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("exit")){
                System.out.println("The program ended.");
                break;
            }

            try {
                if(input.startsWith("add ")){
                    String message = input.substring(4).trim();
                    addMessage(messages, message);
                } else if(input.equalsIgnoreCase("show")){
                    showMessage(messages);
                } else if(input.equalsIgnoreCase("undo")){
                    undoMessage(messages);
                } else {
                    System.out.println("Invalid commmand!!!");
                }
            } catch (EmptyMessageException e){
                System.out.println("!!!" + e.getMessage());
            }
        }
    }

    private static void undoMessage(List<String> messages) {
        if(messages.isEmpty()) {
            System.out.println("There is no messages for deleting.");
        } else {
            String removed = messages.remove(messages.size() - 1);
            System.out.println("Deleted the last message: " + removed + "\"");
        }
    }

    private static void showMessage(List<String> messages) {
        if(messages.isEmpty()){
            System.out.println("There is no messages.");
        } else {
            System.out.println("All messages: ");
            for (int i = 0; i < messages.size(); i++){
                System.out.println((i + 1) + ". " + messages.get(i));
            }
        }
    }

    private static void addMessage(List<String> messages, String message) throws EmptyMessageException{
        if(message == null || message.trim().isEmpty()){
            throw new EmptyMessageException("The message can not be empty!");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(message);

        messages.add(sb.toString());
        System.out.println("Added message: \"" + sb + "\"");
    }
}
