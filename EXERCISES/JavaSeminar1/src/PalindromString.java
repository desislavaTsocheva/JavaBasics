import java.util.Scanner;

public class PalindromString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();
        String palindrom = new StringBuilder(s).reverse().toString();
        if(palindrom.equals(s)){
            System.out.println("Palindrom");
        }
        else{
            System.out.println("Not a palindrom");
        }
    }
}
