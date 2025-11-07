import java.util.Scanner;

public class TempConverter {
    public static final double KELVIN_OFFSET=275.15;

    public static double toCel(double kel){
        return kel-KELVIN_OFFSET;
    }
    public static double toFar(double cel){
        return (cel*9/5)+32;
    }
    public static double toCelFromFar(double far){
        return (far-32)*5/9;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter input:");
        double cel=sc.nextDouble();
        double kel=cel+KELVIN_OFFSET;
        double far=toFar(cel);

        System.out.println(Math.round(cel)+" C");
        System.out.println(Math.round(far)+" F");
        System.out.println(Math.round(kel)+" K");
    }
}