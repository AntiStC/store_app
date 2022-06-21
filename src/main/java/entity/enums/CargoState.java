package entity.enums;

public enum CargoState {
    //placeholders
    READY(0), NOT_READY(1);

    public final int intValue;

    public int getIntValue() {
        return intValue;
    }

    CargoState(int intValue) {
        this.intValue = intValue;
    }
}
