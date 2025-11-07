import java.security.PublicKey;
import java.util.Scanner;

public class CurrencyConverter {
    private static final double USD_TO_EUR=0.93;
    private static final double EUR_TO_BGN=1.96;

    public static double convertUSDToEUR(double amount) {
        return amount*USD_TO_EUR;
    }
    public static double convertEURToBGN(double amount) {
        return amount*EUR_TO_BGN;
    }

    public static void main(String[] args) {
     Scanner sc=new Scanner(System.in);
     System.out.println("Enter USD amount: ");
     double amount=sc.nextDouble();
     System.out.println("Enter EUR amount: ");
     double amountEUR=sc.nextDouble();
     System.out.println(convertUSDToEUR(amount));
     System.out.println(convertEURToBGN(amountEUR));
    }
}
