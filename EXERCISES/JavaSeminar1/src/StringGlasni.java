import java.util.Scanner;

public class StringGlasni {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string: ");
        String s = sc.nextLine();
        int counter=0;
        for(int i=0;i<s.length();i++){
            char text=s.charAt(i);
            if("aeiuo".indexOf(text)!=1){
                counter++;
            }
        }
        System.out.println("Counter:"+ counter);

    }
}
