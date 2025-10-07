import java.security.PublicKey;
import java.util.Scanner;

public class BankAccount {
    private String owner;
    private double balance;
    public static final double INTEREST_RATE=0.02;
    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }
    public static double CalculateInterest(double interestRate) {
        return interestRate * INTEREST_RATE;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter interest rate: ");
        double interestRate = sc.nextDouble();
        System.out.println("Customer interest is: "+CalculateInterest(interestRate));
    }
}
