import java.security.PublicKey;

public interface Extras {
    enum ACType {MANUAL, AUTOMATIC}
    enum Interior {FABRIC, LEATHER}
    enum RimType {STEEL, ALLOY}

    default String getACManual() {return ACType.MANUAL.name();}
    default String getACAutomatic() {return ACType.AUTOMATIC.name();}
    default String getInteriorFabric(){return Interior.FABRIC.name();}
    default String getInteriorLeather(){return Interior.LEATHER.name();}
    default String getRimTypeSteel(){return RimType.STEEL.name();}
    default String getRimTypeAlloy(){return RimType.ALLOY.name();}

    default double extrasPrice(ACType acType, Interior interior, RimType rimType) {
        double totalPrice=0;
        if (acType == ACType.MANUAL) {
            return totalPrice *= 500;
        } else if (acType == ACType.AUTOMATIC) {
            return totalPrice *= 900;
        }
        if(interior == Interior.FABRIC) {
            return totalPrice;
        }
        else if(interior == Interior.LEATHER) {
            return totalPrice *=1200;
        }
        if(rimType == RimType.STEEL) {
            return totalPrice;
        }
        else if(rimType == RimType.ALLOY) {
            return totalPrice *=600;
        }
        return totalPrice;
    }
}
