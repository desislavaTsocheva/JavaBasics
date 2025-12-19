import java.util.ArrayList;
import java.util.List;

public class BarLogic {
    private List<Drink> drinks=new ArrayList<Drink>();

    public void isAvailable() {
        for (Drink drink : drinks) {
            if(drink.getAmount()>0){
                System.out.println(drink +" is available");
            }
            else{
                System.out.println("Drink "+drink.getName()+" is not available");
            }
        }
    }

    public void sendRequest(int id, int amount) {
        for (Drink drink : drinks) {
            if(drink.getId()==id&&drink.getAmount()<amount){
                drink.addQuantity(amount);
                System.out.println("Your request has been sent");
            }
            else{
                System.out.println("We cannot send this drink");
            }
        }
    }

    




}
