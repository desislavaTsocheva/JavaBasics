import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Account {
    private final String accountNumber;
    private final String ownerName;
    private double balance;
    private final List<String> history;

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.history = new ArrayList<>();
    }

    public synchronized double getBalance() { return balance; }

    public synchronized void deposit(double amount) {
        balance += amount;
        addHistory("Получени: " + amount + " лв.");
    }

    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            addHistory("Изпратени: " + amount + " лв.");
            return true;
        }
        return false;
    }

    public synchronized void addHistory(String entry) {
        history.add(entry);
    }

    public synchronized List<String> getHistory() {
        return new ArrayList<>(history);
    }
}