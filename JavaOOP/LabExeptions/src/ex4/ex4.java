package ex4;

import java.util.Scanner;

public class ex4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student registry = new Student();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добави студент");
            System.out.println("2. Покажи всички студенти");
            System.out.println("3. Изход");
            System.out.print("Избор: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Име: ");
                    String name = sc.nextLine();

                    System.out.print("Факултетен номер: ");
                    String facNumber = sc.nextLine();

                    System.out.print("Специалност: ");
                    String specialty = sc.nextLine();

                    try {
                        registry.addStudent(new Student(name, facNumber, specialty));
                        System.out.println("Студентът е добавен успешно!");
                    } catch (FacNumberDublication e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                    break;

                case 2:
                    registry.showAllStudents();
                    break;

                case 3:
                    System.out.println("Изход...");
                    return;

                default:
                    System.out.println("Невалиден избор!");
            }
        }
    }
}
