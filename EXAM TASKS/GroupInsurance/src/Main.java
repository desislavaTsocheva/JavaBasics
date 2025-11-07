import Exceptions.InvalidEmailException;
import Services.Company;
import Services.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("WELCOME TO THE GROUP INSURANCE");
        System.out.println("------------------------------");
        System.out.println("Choose #1 to create new company");
        System.out.println("Choose #2 to add employee");
        System.out.println("Choose #3 to print all employees");
        System.out.println("Choose #4 to search employee");
        System.out.println("Choose #5 to exit the program");
        int choice;

        List<Employee> employees=new ArrayList<>();
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("Create company");
        System.out.println("Enter the name of the company");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Enter the domain of the company");
        String domain = new Scanner(System.in).nextLine();
        Company company = new Company(name, domain, employees);
        boolean exit = false;
        while(!exit) {
            do {
                System.out.println("Choice: ");
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
            } while (choice < 1 || choice > 5);
            switch (choice) {
                case 1:
                    System.out.println("Create company");
                    System.out.println("Enter the name of the company");
                    String nameC = new Scanner(System.in).nextLine();
                    System.out.println("Enter the domain of the company");
                    String domainC = new Scanner(System.in).nextLine();
                    company = new Company(nameC, domainC, employees);
                    break;

                case 2:
                    try {
                        System.out.println("Add employee");
                        System.out.println("Enter the name of the employee");
                        String employeeName = new Scanner(System.in).nextLine();
                        System.out.println("Enter the id of the employee");
                        String employeeId = new Scanner(System.in).nextLine();
                        System.out.println("Enter the experience of the employee");
                        int year = new Scanner(System.in).nextInt();
                        System.out.println("Enter the email of the employee");
                        String employeeEmail = new Scanner(System.in).nextLine();

                        Employee employee = new Employee(employeeName, employeeId, year, employeeEmail);
                        company.addEmployee(employee);
                        break;
                    } catch (InvalidEmailException e) {
                        System.out.println("Invalid email: " + e.getMessage());
                        System.out.println("Please try again.\n");
                    } catch (IllegalArgumentException e) {
                        System.out.println(" " + e.getMessage());
                        System.out.println("Please try again.\n");
                    } catch (Exception e) {
                        System.out.println("Unexpected error: " + e.getMessage());
                        System.out.println("Please try again.\n");
                    }
                    break;

            case 3:
                    System.out.println("Print all employees");
                    company.listEmployees();
                    break;

                case 4:
                    System.out.println("Search employee");
                    System.out.println("Enter the id of the employee");
                    String id = new Scanner(System.in).nextLine();
                    company.findById(id);
                    break;

                case 5:
                    System.out.println("Exit the program");
                    exit = true;
                    break;
            }
        }
    }
}