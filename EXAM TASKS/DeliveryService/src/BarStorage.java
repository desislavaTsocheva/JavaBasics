import java.util.*;

public class BarStorage {
    private static Map<Integer, Drink> drinks = Collections.synchronizedMap(new HashMap<>());

    static {
        drinks.put(1, new Drink(1, "Vodka", 12, Types.Alcohol));
        drinks.put(2, new Drink(2, "Martini", 20, Types.Cocktail));
        drinks.put(3, new Drink(3, "Cola", 50, Types.Nonalcohol));
    }

    public static Collection<Drink> getAllDrinks() {
        return drinks.values();
    }

    public static Drink getDrink(int id) {
        return drinks.get(id);
    }
}
