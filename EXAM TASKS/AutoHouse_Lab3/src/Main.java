import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Dealership dealership = new Dealership();

        System.out.println("=== Enter Car Information ===");

        for (int i = 0; i < 1; i++) {
            System.out.println("\n--- Diesel Car #" + (i + 1) + " ---");
            dealership.addCar(readDieselCar());
            System.out.println("\n--- Petrol Car #" + (i + 1) + " ---");
            dealership.addCar(readPetrolCar());
            System.out.println("\n--- Electric Car #" + (i + 1) + " ---");
            dealership.addCar(readElectricCar());
        }

        System.out.println("\n=== All Cars ===");
        dealership.listAllCars();

        System.out.printf("\nTotal eco tax amount: %.2f BGN%n", dealership.ecoTaxSum());

        System.out.println("\n=== Filter by Extra ===");
        System.out.print("Choose category (AC / INTERIOR / RIMS): ");
        String category = sc.next();

        System.out.println("\nResults:");
        List<Car> filtered = dealership.filterByExtra(category);
        if (filtered.isEmpty()) System.out.println("No matches found.");
        else filtered.forEach(System.out::println);
    }

    private static DieselCar readDieselCar() {
        return new DieselCar(
                readString("Make"),
                readString("Model"),
                readInt("Year of production"),
                readDouble("Engine capacity (ccm)"),
                readDouble("Price"),
                readAC(), readInterior(), readRims());
    }

    private static PetrolCar readPetrolCar() {
        return new PetrolCar(
                readString("Make"),
                readString("Model"),
                readInt("Year of production"),
                readDouble("Engine capacity (ccm)"),
                readDouble("Price"),
                readAC(), readInterior(), readRims());
    }

    private static ElectricCar readElectricCar() {
        return new ElectricCar(
                readString("Make"),
                readString("Model"),
                readInt("Year of production"),
                0,
                readDouble("Price"),
                readDouble("Battery capacity (kWh)"),
                readAC(), readInterior(), readRims());
    }


    private static int readInt(String label) {
        while (true) {
            System.out.print(label + ": ");
            String input = sc.nextLine().trim();
            try {
                int year= Integer.parseInt(input);
                if(year<1900 || year>2026){
                    throw new NumberFormatException();
                }
                return year;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static double readDouble(String label) {
        while (true) {
            System.out.print(label + ": ");
            String input = sc.nextLine().trim();
            try {
                double i= Double.parseDouble(input);
                if(i<0.0){
                    throw new NumberFormatException();
                }
                return i;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    private static String readString(String label) {
        System.out.print(label + ": ");
        return sc.next();
    }

    private static Extras.ACType readAC() {
        while (true) {
            System.out.print("Air conditioning type (MANUAL/AUTOMATIC): ");
            try {
                return Extras.ACType.valueOf(sc.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Enter MANUAL or AUTOMATIC.");
            }
        }
    }

    private static Extras.Interior readInterior() {
        while (true) {
            System.out.print("Interior (FABRIC/LEATHER): ");
            try {
                return Extras.Interior.valueOf(sc.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Enter FABRIC or LEATHER.");
            }
        }
    }

    private static Extras.RimType readRims() {
        while (true) {
            System.out.print("Rims (STEEL/ALLOY): ");
            try {
                return Extras.RimType.valueOf(sc.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Enter STEEL or ALLOY.");
            }
        }
    }
}
