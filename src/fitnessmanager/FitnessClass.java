package fitnessmanager;

import java.util.ArrayList;

/**
 * FitnessClass Class is a blueprint for FitnessClass objects
 * Stores member databases for class as well as information
 * pertaining to class:
 *  - Type of Class
 *  - Instructor Name
 *  - Time it's held
 *  - Location
 * Contains separate arraylists for storing members and guests
 * Includes methods to check in and drop classes
 * @author Arya Shetty, John Greaney-Cheng
 */
public class FitnessClass{
    private ClassName className;
    private Instructor instructor;
    private Time time;
    private Location location;

    private ArrayList<Member> memberList;
    private ArrayList<Member> guestList;

    /**
     * Creates a FitnessClass instance
     * @param className type of class (e.g. Pilates)
     * @param instructor name of the class' instructor
     * @param time the time of the class
     * @param location the location of the class
     */
    public FitnessClass(String className, String instructor, String time, String location){
        this.memberList = new ArrayList<>();
        this.guestList = new ArrayList<>();
        this.className = ClassName.stringToClassName(className);
        this.instructor = Instructor.stringToInstructor(instructor);
        this.time = Time.stringToTime(time);
        this.location = Location.stringToLocation(location);
    }

    /**
     * Creates a FitnessClass instance
     * @param className type of class (e.g. Pilates)
     * @param instructor name of the class' instructor
     * @param location the location of the class
     */
    public FitnessClass(String className, String instructor, String location){
        this.memberList = new ArrayList<>();
        this.guestList = new ArrayList<>();
        this.className = ClassName.stringToClassName(className);
        this.instructor = Instructor.stringToInstructor(instructor);
        this.location = Location.stringToLocation(location);
    }

    /**
     * Prints out schedule of classes for the day the gym is run
     * Includes the class name, instructor name, time,
     * followed by all the members and guests that have checked into the class
     * Only print out member and guest lists if they're nonempty
     * Need if statement to print "hour:00" instead of "hour:0" if minute = 0
     */
    public void print(){
        if (time.getMinute() == 0){
            System.out.println(className.name() + " - " + instructor.name() + ", " + time.getHour()
                    + ":" + "00, " + location.name());
        }
        else {
            System.out.println(className.name() + " - " + instructor.name() + ", " + time.getHour()
                    + ":" + time.getMinute() + ", " + location.name());
        }
        if (!this.memberList.isEmpty()){
            System.out.println("- Participants - ");
            for (Member member: memberList){
                System.out.println(member.toString());
            }
        }
        if (!this.guestList.isEmpty()) {
            System.out.println("- Guests - ");
            for (Member member: guestList){
                System.out.println(member.toString());
            }
        }
    }

    /**
     * Checks into the class the member wants to be in,
     * adds member to the specific database for class
     * @param member, a member that wants to check into one of the fitness classes
     * @param cName, a FitnessOptions enum that represents the fitness class that the member wants to check into
     * @return true if class was checked in, false if class was not checked into
     */
    public boolean checkIn(Member member) {
        if (this.memberList.contains(member)){
            return false;
        }
        this.memberList.add(member);
        return true;
    }

    public boolean checkOut(Member member){
        return this.memberList.remove(member);
    }

    public void checkInGuest(Member member){
        this.guestList.add(member);
    }

    public boolean checkOutGuest(Member member){
        return this.guestList.remove(member);
    }

    /**
     * Checks if member is already stored in database
     * Goes through database list to see if any stored member is
     * equal to given member
     * @param member the member to find in the database
     * @return the index of the member in the list,
     *         -1 if the member is not in the list
     */
    public Member find(Member member) {
        for (Member m: memberList){
            if (member.equals(m)){
                return m;
            }
        }
        return null;
    }

    public Member findGuest(Member member) {
        for (Member m: guestList){
            if (member.equals(m)){
                return m;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof FitnessClass){
            FitnessClass fc = (FitnessClass) obj;
            return (fc.getInstructor().equals(this.getInstructor())
                    && fc.getClassName().equals(this.getClassName())
                    && fc.getLocation().equals(this.getLocation()));
        }
        return false;
    }


    public ClassName getClassName(){
        return this.className;
    }

    public Instructor getInstructor(){
        return this.instructor;
    }

    public Time getTime(){
        return this.time;
    }

    public Location getLocation(){
        return this.location;
    }
}



