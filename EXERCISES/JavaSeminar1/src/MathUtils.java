import java.util.Scanner;

public class MathUtils {

    public static int min(int a, int b) {
        return Math.min(a, b);
        /*
        if(a<b){
            return a;
        }
        else{
            return b;
        }*/


    }
    public static int max(int a, int b) {
        /*
        if(a>b){
            return a;
        }
        else{
            return b;
        }
        */
        return Math.max(a, b);
    }
    public static int power(int base, int exponent) {
        /*int res=1;
        while (exponent > 0) {
            res *= base;
        }
        return res;
        */
        return (int) Math.pow(base, exponent);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a: ");
        int a = sc.nextInt();
        System.out.println("Enter b: ");
        int b = sc.nextInt();
        System.out.println("Enter base: ");
        int base = sc.nextInt();
        System.out.println("Enter exponent: ");
        int exp = sc.nextInt();
        int min = min(a, b);
        System.out.println("Min: "+min);
        int max = max(a, b);
        System.out.println("Max: "+max);
        int power = power(base, exp);
        System.out.println("Power: "+power);

    }
}
