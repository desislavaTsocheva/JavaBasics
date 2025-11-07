package ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsernameValidator {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        List<String> validUsernames = new ArrayList<>();
        System.out.println("Enter usernames (to stop enter 'end'): ");

        while(true){
            String username = scanner.nextLine();
            if(username.equalsIgnoreCase("end")) break;

            try {
                if(isValid(username)){
                    System.out.println("Valid username: " + username);
                    validUsernames.add(username);
                }
            } catch (InvalidUsernameExeption e){
                System.out.println("Invalid username: " + e.getMessage());
            }
        }
        System.out.println("\nList of valid usernames: ");
        if(validUsernames.isEmpty()){
            System.out.println("There are no valid usernames!");
        } else {
            for (String name : validUsernames) {
                System.out.println(" - " + name);
            }
        }

        System.out.println("\nThe number of valid usernames:  " + validUsernames.size());
    }

    public static boolean isValid(String username) throws InvalidUsernameExeption {
        if (username.length() < 5 || username.length() > 15) {
           throw new InvalidUsernameExeption("The length must be between 5 and 15.");
        }

        if (!Character.isLetter(username.charAt(0))) {
            throw new InvalidUsernameExeption("The first symbol must be a letter.");
        }

        for(char ch : username.toCharArray()){
            if(!Character.isLetterOrDigit(ch) && ch != '_'){
                throw new InvalidUsernameExeption("Only letters, numbers or underscores are allowed!");
            }
        }
        return true;
    }
}
