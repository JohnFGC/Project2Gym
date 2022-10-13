package fitnessmanager;

/**
 * Constant Enum Class holds all variables with constant values
 * Stores the value corresponding to each constant
 * @author Arya Shetty, John Greaney-Cheng
 */
public enum Constant {
    QUADRENNIAL(4),
    CENTENNIAL(100),
    QUARTERCENTENNIAL(400),
    LEAP_DAY(29),
    FIRST_DAY_OF_MONTH(1),
    MINIMUM_AGE(18),
    STARTING_CAPACITY(4),
    INCREASE_CAPACITY(4);


    private final int value;

    /**
     * Creates a Constant
     * @param value the corresponding value of the constant
     */
    Constant(int value){
        this.value = value;
    }

    /**
     * Getter method for value of constant
     * @return constant's value
     */
    public int getValue(){
        return this.value;
    }
}
