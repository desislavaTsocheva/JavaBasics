package task_5;

import java.util.ArrayList;

public class MainTask5 {
    public static void main(String[] args) {
        ArrayList<Worker> workers = new ArrayList<>();
        Intern intern = new Intern();
        intern.setBaseSalary(2000);
        workers.add(intern);

        Manager manager = new Manager();
        manager.setBaseSalary(5000);
        workers.add(manager);

        for (Worker worker : workers) {
            System.out.println("The salary is: " + worker.calculateTotalPay());
        }
    }
}
