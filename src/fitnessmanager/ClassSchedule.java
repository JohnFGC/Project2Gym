package fitnessmanager;

/**
 * ClassSchedule is a class to store and sort fitness classes
 * Stores the following data:
 *  - FitnessClass list
 *  - Size of list
 * @author Arya Shetty, John Greaney-Cheng
 */
public class ClassSchedule {
    private FitnessClass[] classes;
    private int numClasses;

    /**
     * Creates a Class Schedule instance
     * Array starting capacity is 4
     */
    public ClassSchedule(){
        this.numClasses = 0;
        this.classes = new FitnessClass[Constant.STARTING_CAPACITY.getValue()];
    }

    /**
     * Adds a class into the schedule
     * If the list is at full capacity, grow list by 4
     * @param fc the class to add into the schedule
     */
    public void addClass(FitnessClass fc){
        classes[numClasses] = fc;
        numClasses++;
        if(numClasses == classes.length){
            grow();
        }
    }

    /**
     * Increases the size of the schedule list by four to store new classes
     * Creates a new FitnessClass array with the increased capacity
     * then stores the old classes in the list, and replaces the
     * old database list with the new one
     */
    private void grow(){
        int newLength = Constant.INCREASE_CAPACITY.getValue() + this.numClasses;
        FitnessClass[] newList = new FitnessClass[newLength];
        for (int i = 0; i < this.classes.length; i++) {         //Enters old class info into new larger list
            newList[i] = this.classes[i];
        }
        this.classes = newList;
    }

    public FitnessClass classExists(FitnessClass fc){
        for(int i = 0; i < numClasses; i++){
            if(fc.equals(classes[i])){
                return classes[i];
            }
        }
        return null;
    }

    public FitnessClass findMemberClass(Member member){
        for(int i = 0; i < numClasses; i++){
            if(classes[i].find(member) != null){
                return classes[i];
            }
        }
        return null;
    }

    /**
     * Prints out schedule of classes for the day the gym is run
     * Includes the class name, instructor name, time,
     * followed by all the members that have checked into the class
     */
    public void printClasses(){
        for(int i = 0; i < numClasses; i++){
            classes[i].print();
        }
    }

    /**
     * Checks if this schedule is empty
     * @return true if this schedule is empty, false otherwise
     */
    public boolean isEmpty(){
        return (this.numClasses == 0);
    }
}
