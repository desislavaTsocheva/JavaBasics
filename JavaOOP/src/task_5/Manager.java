package task_5;

public class Manager extends Worker{
    @Override
    public double calculateTotalPay() {
        return baseSalary + 0.2 * baseSalary;
    }
}
