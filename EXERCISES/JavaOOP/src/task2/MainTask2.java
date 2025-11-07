package task2;

import task_1.Guitar;
import task_1.Instrument;
import task_1.Piano;

import java.util.Scanner;

public class MainTask2 {
    public static void main(String[] args) {
        System.out.println("Enter number of appliances:");
        int num = new Scanner(System.in).nextInt();
        Appliances[] app = new Appliances[num];
        for (int i = 0; i < num / 2; i++) {
            app[i] = new WashingMachine(5,34) {
            };
        }
        for (int i = num / 2; i < num; i++) {
            app[i] = new Refrigerator(33,6) {
            };
        }
        for (int i = 0; i < num; i++) {
            app[i].getPowerUsages();
            app[i].getPrice();
        }
    }
}
