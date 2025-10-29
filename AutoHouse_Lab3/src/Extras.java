import java.security.PublicKey;

public interface Extras {
    enum ACType {MANUAL, AUTOMATIC}
    enum Interior {FABRIC, LEATHER}
    enum RimType {STEEL, ALLOY}

    ACType getACType();
    Interior getInterior();
    RimType getRimType();

    default double extrasPrice() {
        double price = 0;
        switch (getACType()) {
            case MANUAL -> price += 500;
            case AUTOMATIC -> price += 900;
        }
        switch (getInterior()) {
            case LEATHER -> price += 1200;
        }
        switch (getRimType()) {
            case ALLOY -> price += 600;
        }
        return price;
    }
}
