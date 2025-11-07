package ex7;

import java.util.LinkedList;
import java.util.Queue;

public class UserHistory implements ActionHistory{
    private static final int MAX_ACTION = 5;
    private Queue<String> actions = new LinkedList<>();
    @Override
    public void addAction(String action){
        if(actions.size() == MAX_ACTION){ actions.poll(); }
        actions.add(action);
        showHistory();
    }
    public void showHistory(){
        System.out.println("\nCurrent history: ");
        if(actions.isEmpty()){
            System.out.println("The history is empty.");
        } else {
            for(String a : actions){
                System.out.println(" - " + a);
            }
        }
    }
}
