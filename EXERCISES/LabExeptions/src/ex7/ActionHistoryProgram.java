package ex7;

import java.util.Scanner;

public class ActionHistoryProgram {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        UserHistory history = new UserHistory();

        System.out.println("Enter action(to stop enter 'end'): ");

        while (true){
            String input = scanner.nextLine();

            if(input.equalsIgnoreCase("exit")){
                System.out.println("The program is ended.");
                break;
            }

            if(!input.isEmpty()){
                history.addAction(input);
            }
        }
    }
}
