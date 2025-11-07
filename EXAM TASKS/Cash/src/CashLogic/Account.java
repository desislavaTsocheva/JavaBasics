package CashLogic;

public class Account {
    private double balance;
    private int pin;
    private String accountName;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Account(double balance, String accountName, int pin) {
        this.balance = balance;
        this.accountName = accountName;
        this.pin = pin;
    }

    public double printBalance(){
        return balance;
    }

    public double deposit(double amount){
        balance += amount;
        return balance;
    }
    public double withdraw(double amount){
        balance -= amount;
        return balance;
    }
    public int checkPin(int pin){
        if(pin == this.pin){
            return 1;
        }
        return 0;
    }
}
