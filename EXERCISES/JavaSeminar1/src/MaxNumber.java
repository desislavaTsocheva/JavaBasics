import java.util.Scanner;

public class MaxNumber {
    public static void main(String[] args) {
        int max=0;

        for(int i=1;i<=5;i++){
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number: ");
            int num = sc.nextInt();
            if(max<num){
                max=num;
            }
        }
        System.out.println(max);
    }
}
