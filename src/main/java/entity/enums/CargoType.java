package entity.enums;

public enum CargoType {
    //placeholders
    ROUND(0), SQUARE(1);

    public final int intValue;

    public int getIntValue(){
        return intValue;
    }

    CargoType(int intValue){
        this.intValue = intValue;
    }
}
