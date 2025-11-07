import java.util.Scanner;

public class AverageRes {
    public static void main(String[] args) {
        int average=0;
        int sum=0;
        int count=0;
        for(int i=0;i<10;i++){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter Res Number");
            int res=input.nextInt();
            sum+=res;
            count++;
        }
        average=sum/count;
        System.out.println("Average Res is "+average);
    }
}
