package fitnessmanager;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

/**
 * User Interface class that processes commands entered into the console
 * Focuses on commands relating to gym member management,
 * such as Add Member, Remove Member, Check In Member
 * @author Arya Shetty, John Greaney-Cheng
 */
public class GymManager {
    private MemberDatabase db;                          //Member Database used to keep track of members in gym
    private ClassSchedule cs;

    /**
     * Creates an instance of the GymManager Class
     */
    public GymManager(){
        this.db = new MemberDatabase();
        this.cs = new ClassSchedule();
    }

    /**
     * Continually processes user inputted commands
     * Terminates when user enters command to quit
     * Commands are checked with two helper methods:
     * Old (Same Functionality as Project 1)
     * New (New Project 2 Functionality)
     * Checks old commands first, if old command isn't run,
     * then checks new commands
     * Note: Invalid Command and Quit are in new helper method
     */
    public void run(){
        System.out.println("Gym Manager Running...");
        Scanner sc = new Scanner(System.in);
        boolean qValue = false;
        while (!qValue){                               //Checks if user has given command to quit
            String command = sc.next();
            if (!checkCommandOld(sc, command)){
                qValue = checkCommandNew(sc, command);
            }
        }
        sc.close();
        System.out.println("Gym Manager terminated.");
    }

    /**
     * Checks and runs if command is from Project 1
     * @param sc scanner to receive user input
     * @param command the command to check and run
     * @return true if a command was run, false otherwise
     */
    private boolean checkCommandOld(Scanner sc, String command){
        switch (command){
            case "R":                                   //Remove member from database
                remove(sc.next(), sc.next(), sc.next());
                return true;
            case "P":                                   //Print unsorted list of members from database
                printMemberList();
                return true;
            case "PC":                                  //Print list of members from database sorted by county
                printSortedMemberList(SortCategory.COUNTY);
                return true;
            case "PN":                                  //Print list of members from database sorted by name
                printSortedMemberList(SortCategory.NAME);
                return true;
            case "PD":                                  //Print list of members from database sorted by expiration date
                printSortedMemberList(SortCategory.EXPIRATION_DATE);
                return true;
            case "S":                                   //Print fitness class schedule
                printSchedule();
                return true;
        }
        return false;
    }

    /**
     * Checks and runs if command is from (or updated for) Project 2
     * @param sc scanner to receive user input
     * @param command the command to check and run
     * @return true if command was given to terminate, false otherwise
     */
    private boolean checkCommandNew(Scanner sc, String command){
        switch (command){
            case "A":                                   //Add member to database
                addStandard(sc.next(), sc.next(), sc.next(), sc.next());
                break;
            case "LS":
                loadSchedule();
                break;
            case "LM":
                loadMemberList();
                break;
            case "AF":
                addFamily(sc.next(), sc.next(), sc.next(), sc.next());
                break;
            case "AP":
                addPremium(sc.next(), sc.next(), sc.next(), sc.next());
                break;
            case "PF":
                printMemberWithFees();
                break;
            case "C":                                   //Check in member to a fitness class
                checkIn(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
                break;
            case "CG":
                checkInGuest(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
                break;
            case "D":                                   //Drops member from fitness class
                checkOut(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
                break;
            case "DG":
                checkOutGuest(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
                break;
            case "Q":                                   //Quit user interface
                return true;
            default:                                    //Otherwise, command is invalid
                invalidCommand(command);
        }
        return false;
    }

    /**
     * Adds standard gym member to database
     * Checks if member can be added first
     * @param fname first name of the member to add
     * @param lname last name of the member to add
     * @param dob birthday of the member to add
     * @param location the gym the member belongs to
     */
    private void addStandard(String fname, String lname, String dob, String location){
        if (canBeAdded(dob, location)){
            Date expire = getExpirationDate(MembershipType.STANDARD);
            if (this.db.add(new Member(fname, lname, dob, expire, location))){               //Checks if member has already been added to database
                System.out.println(fname + " " + lname + " added.");
            }
            else {
                System.out.println(fname + " " + lname + " is already in the database.");
            }
        }
    }

    /**
     * Adds family gym member to database
     * Checks if member can be added first
     * @param fname first name of the member to add
     * @param lname last name of the member to add
     * @param dob birthday of the member to add
     * @param location the gym the member belongs to
     */
    private void addFamily(String fname, String lname, String dob, String location){
        if (canBeAdded(dob, location)){
            Date expire = getExpirationDate(MembershipType.FAMILY);
            if (this.db.add(new Family(fname, lname, dob, expire.toString(), location))){               //Checks if member has already been added to database
                System.out.println(fname + " " + lname + " added.");
            }
            else {
                System.out.println(fname + " " + lname + " is already in the database.");
            }
        }
    }

    /**
     * Adds premium gym member to database
     * Checks if member can be added first
     * @param fname first name of the member to add
     * @param lname last name of the member to add
     * @param dob birthday of the member to add
     * @param location the gym the member belongs to
     */
    private void addPremium(String fname, String lname, String dob, String location){
        if (canBeAdded(dob, location)){
            Date expire = getExpirationDate(MembershipType.PREMIUM);
            if (this.db.add(new Premium(fname, lname, dob, expire.toString(), location))){               //Checks if member has already been added to database
                System.out.println(fname + " " + lname + " added.");
            }
            else {
                System.out.println(fname + " " + lname + " is already in the database.");
            }
        }
    }

    /**
     * Checks if member meets all conditions to be added
     * Conditions include:
     * - Both birthday and expiration date of membership must be valid dates
     * - Member's birthday can't be today or future date
     * - Member must be over 18
     * - The gym location the member belongs to must exist
     * @param dob birthday of the member
     * @param location the gym the member belongs to
     * @return true if member meets conditions to be added, false otherwise
     */
    private boolean canBeAdded(String dob, String location){
        Date birthday = new Date(dob);
        if (!birthday.isValid()){                                                       //Checks if birthday date is a valid date
            System.out.println("DOB " + dob + ": invalid calendar date!");
            return false;
        }
        Date today = new Date();
        if (birthday.compareTo(today) >= 0){                                            //Checks if birthday date is before today
            System.out.println("DOB " + dob + ": cannot be today or a future date!");
            return false;
        }
        if (!isOverEighteen(birthday, today)){                                          //Checks if member is over 18
            System.out.println("DOB " + dob + ": must be 18 or older to join!");
            return false;
        }
        if (Location.stringToLocation(location) == null){                                                //Checks if member's gym location exists
            System.out.println(location + ": invalid location!");
            return false;
        }
        return true;
    }

    /**
     * Removes gym member from database
     * Checks if gym member is in database,
     * if so removes them
     * @param fname first name of the member to remove
     * @param lname last name of the member to remove
     * @param dob birthday of the member to remove
     */
    private void remove(String fname, String lname, String dob){
        if (this.db.remove(new Member(fname, lname, dob))){                                  //Checks if member exists in the database
            System.out.println(fname + " " + lname + " removed.");
        }
        else{
            System.out.println(fname + " " + lname + " is not in the database.");
        }
    }

    /**
     * Prints out unsorted list of members
     * Checks if list is empty,
     * if not, prints unsorted list of members
     */
    private void printMemberList(){
        if (this.db.isEmpty()){
            System.out.println("Member Database is empty!");
            return;
        }
        System.out.println("-list of members-");
        this.db.print();
        System.out.println("-end of list-");
    }

    /**
     * Prints out sorted list of members ordered by county their gym is in (alphabetically),
     * Sorts can be based on
     * - county and zip code
     * - membership expiration date
     * - name of members
     * First checks if list is empty,
     * if not, prints list sorted by input parameter
     * @param category the category the members are sorted by
     */
    private void printSortedMemberList(SortCategory category){
        if (this.db.isEmpty()){
            System.out.println("Member Database is empty!");
            return;
        }
        switch (category){
            case COUNTY:
                System.out.println("-list of members sorted by county and zipcode-");
                this.db.sortedPrint(SortCategory.COUNTY);
                break;
            case NAME:
                System.out.println("-list of members sorted by last name, and first name-");
                this.db.sortedPrint(SortCategory.NAME);
                break;
            case EXPIRATION_DATE:
                System.out.println("-list of members sorted by membership expiration date-");
                this.db.sortedPrint(SortCategory.EXPIRATION_DATE);
                break;
        }
        System.out.println("-end of list-");
    }

    /**
     * Prints out unsorted list of members with their fees and guest passes
     * Checks if list is empty,
     * if not, prints unsorted list of members with fees
     */
    private void printMemberWithFees(){
        if (this.db.isEmpty()){
            System.out.println("Member Database is empty!");
            return;
        }
        System.out.println("-list of members with membership fees-");
        this.db.printWithFees();
        System.out.println("-end of list-");
    }

    /**
     * Checks in member to online class
     * First checks if member can check into class
     * @param className name of the class to check into
     * @param fname first name of the member to check in
     * @param lname last name of the member to check in
     * @param dob birthday of the member to check in
     */
    private void checkIn(String className, String instructorName, String location, String fname, String lname, String dob){
        if (canCheckIn(className, instructorName, location, fname, lname, dob)
                && !classConflict(className, instructorName, location, fname, lname, dob)){
            Member memberToCheckIn = this.db.findMember(new Member(fname, lname, dob));
            if (!(memberToCheckIn instanceof Family)){
                if (!memberToCheckIn.getLocation().equals(Location.stringToLocation(location))){
                    Location restrictedLocation = Location.stringToLocation(location);
                    System.out.println(fname + " " + lname + " checking in " + restrictedLocation.toString()
                            + " - standard membership location restriction.");
                    return;
                }
            }
            FitnessClass classToEnrollIn = this.cs.classExists(new FitnessClass(className, instructorName, location));
            if (classToEnrollIn.checkIn(memberToCheckIn)){
                System.out.print(fname + " " + lname + " checked in ");
                classToEnrollIn.print();
            }
            else {
                System.out.println(fname + " " + lname + " already checked in.");
            }
        }
    }

    /**
     * Checks if member meets all conditions to check in
     * Conditions include:
     * - Birthday must be a valid date
     * - Member must exist in member database
     * - Class to check into must exist
     * - Member must have non-expired membership
     * - Member must not have any time conflicts
     *      with other classes they're enrolled in
     * @param className name of the class to check into
     * @param fname first name of the member to check in
     * @param lname last name of the member to check in
     * @param dob birthday of the member to check in
     * @return true if member meets conditions to check into class
     *         false otherwise
     */
    private boolean canCheckIn(String className, String instructorName, String location, String fname, String lname, String dob) {
        Date birthday = new Date(dob);
        if (!birthday.isValid()) {                                                       //Checks if birthday date is a valid date
            System.out.println("DOB " + dob + ": invalid calendar date!");
            return false;
        }

        if (Instructor.stringToInstructor(instructorName) == null) {
            System.out.println(instructorName + " - instructor does not exist.");
            return false;
        }

        Member memberToCheckIn = this.db.findMember(new Member(fname, lname, dob));
        if (memberToCheckIn == null) {
            System.out.println(fname + " " + lname + " " + dob + " is not in the database.");
            return false;
        }

        if (ClassName.stringToClassName(className) == null) {
            System.out.println(className + " class does not exist.");
            return false;
        }

        if (memberToCheckIn.getExpire().compareTo(new Date()) <= 0) {
            System.out.println(fname + " " + lname + " " + dob + " membership expired.");
            return false;
        }

        if (Location.stringToLocation(location) == null) {
            System.out.println(location + " - invalid location.");
            return false;
        }

        if (cs.classExists(new FitnessClass(className, instructorName, location)) == null) {
            System.out.println(className + " by " + instructorName + " does not exist at " + location);
            return false;
        }
        return true;
    }

    public boolean classConflict(String className, String instructorName, String location, String fname, String lname, String dob){
        FitnessClass classToEnrollIn = cs.classExists(new FitnessClass(className, instructorName, location));
        FitnessClass classConflict = cs.findMemberClass(new Member(fname,lname,dob));

        if (classConflict == null || classConflict.equals(classToEnrollIn)){
            return false;
        }

        if (classConflict.getTime().equals(classToEnrollIn.getTime())){
            System.out.println("Time conflict - " + classToEnrollIn.getClassName().name() + " - " + classToEnrollIn.getInstructor().name()
                    + ", " + classToEnrollIn.getTime().toString() +
                    ", " + classToEnrollIn.getLocation().toString());
            return true;
        }
        return false;
    }

    /**
     * Drops member from online class
     * First checks if member meets all conditions to drop class
     * Conditions include:
     * - Birthday must be a valid date
     * - Member must exist in member database
     * - Class to drop must exist
     * - Member must be enrolled in class to drop
     * @param className name of the class to drop
     * @param fname first name of the member to drop from class
     * @param lname last name of the member to drop from class
     * @param dob birthday of the member to drop from class
     */
    private void checkOut(String className, String instructorName, String location, String fname, String lname, String dob) {
       if(canCheckIn(className, instructorName, location, fname, lname, dob)){
           FitnessClass classCheckedIn = cs.classExists(new FitnessClass(className, instructorName, location));
           Member m = this.db.findMember(new Member(fname, lname, dob));
           if(classCheckedIn.find(m) != null){
               classCheckedIn.checkOut(m);
               System.out.println(fname + " " + lname + " done with the class.");
               return;
           }
           System.out.println(fname + " " + lname + " did not check in.");
       }
    }

    private void checkInGuest(String className, String instructorName, String location, String fname, String lname, String dob){
        if(canCheckIn(className, instructorName, location, fname, lname, dob)){
            boolean havePass = false;
            Member memberToCheckIn = this.db.findMember(new Member(fname, lname, dob));

            if(!(memberToCheckIn instanceof Family)){
                System.out.println("Standard membership - guest check-in is not allowed.");
                return;
            }

            if (!Location.stringToLocation(location).equals(memberToCheckIn.getLocation())){
                System.out.println(fname + " " + lname + " Guest checking in " + Location.stringToLocation(location).toString()
                        + " - guest location restriction.");
                return;
            }

            if(memberToCheckIn instanceof Premium){
                havePass = ((Premium) memberToCheckIn).useGuestPass();
            }
            else {
                havePass = ((Family) memberToCheckIn).useGuestPass();
            }
            if (!havePass){
                System.out.println(fname + " " + lname + " ran out of guest pass.");
                return;
            }
            FitnessClass classToEnrollIn = this.cs.classExists(new FitnessClass(className, instructorName, location));
            classToEnrollIn.checkInGuest(memberToCheckIn);
            System.out.print(fname + " " + lname + " (guest) checked in ");
            classToEnrollIn.print();
        }
    }

    private void checkOutGuest(String className, String instructorName, String location, String fname, String lname, String dob){
        if(canCheckIn(className, instructorName, location, fname, lname, dob)){
            Member memberToCheckIn = this.db.findMember(new Member(fname, lname, dob));
            FitnessClass classCheckedIn = this.cs.classExists(new FitnessClass(className, instructorName, location));
            if(classCheckedIn.findGuest(memberToCheckIn) != null){
                classCheckedIn.checkOutGuest(memberToCheckIn);
                if(memberToCheckIn instanceof Premium){
                    ((Premium) memberToCheckIn).returnGuestPass();
                }
                else {
                    ((Family) memberToCheckIn).returnGuestPass();
                }
                System.out.println(fname + " " + lname + " Guest done with the class.");
                return;
            }
            System.out.println(fname + " " + lname + " Guest did not check in.");
        }
    }

    /**
     * Prints the fitness class schedule
     * Checks if class schedule is empty
     * If not, prints class schedule
     */
    private void printSchedule(){
        if (cs.isEmpty()){
            System.out.println("Fitness class schedule is empty");
            return;
        }
        System.out.println("-Fitness classes-");
        cs.printClasses();
        System.out.println("-end of class list.");
    }

    /**
     * Loads the fitness class schedule
     * Imports classes from "classSchedule.txt"
     */
    private void loadSchedule(){
        try {
            File classSchedule = new File(".\\classSchedule.txt");
            Scanner sc = new Scanner(classSchedule);
            System.out.println("-Fitness classes loaded-");
            while (sc.hasNext()){
                FitnessClass fc = new FitnessClass(sc.next(), sc.next(), sc.next(), sc.next());
                this.cs.addClass(fc);
            }
            cs.printClasses();
            System.out.println("-end of class list.");
            sc.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
    }

    /**
     * Loads a list of members to the database
     * Imports members from "memberList.txt"
     */
    private void loadMemberList(){
        try {
            File memberList = new File(".\\memberList.txt");
            Scanner sc = new Scanner(memberList);
            System.out.println("-list of members loaded-");
            while (sc.hasNext()){
                Member toAdd = new Member(sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
                db.add(toAdd);
                System.out.println(toAdd.toString());
            }
            System.out.println("-end of list-");
            sc.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("File Not Found!");
        }

    }

    /**
     * Prints output for invalid user command
     * @param command the invalid user input
     */
    private void invalidCommand(String command){
        System.out.println(command + " is an invalid command!");
    }

    /**
     * Checks if member is over 18 years old
     * Compares birth year and current year to see if
     * difference is greater than or less than 18. If difference
     * is equal to 18, compares months and if months are equal,
     * compares days.
     * @param birthday birthday of the member
     * @param today current day's date
     * @return true if member is 18 or older, false otherwise
     */
    private boolean isOverEighteen(Date birthday, Date today){
        if (today.getYear() - birthday.getYear() > Constant.MINIMUM_AGE.getValue()){
            return true;
        }
        if (today.getYear() - birthday.getYear() < Constant.MINIMUM_AGE.getValue()){
            return false;
        }
        if (today.getMonth() > birthday.getMonth()){
            return true;
        }
        if (today.getMonth() < birthday.getMonth()){
            return false;
        }
        if (today.getDay() >= birthday.getDay()){
            return true;
        }
        return false;
    }

    /**
     * Returns expiration date of newly added member
     * Expiration date depends on membership type of member
     * For Standard and Family, expiration date is three months
     * from today
     * For Premium, expiration date is one year from today
     * @param membershipType memberbership type of member
     * @return expiration date corresponding to membership type
     */
    private Date getExpirationDate(MembershipType membershipType){
        Date today = new Date();
        switch (membershipType){
            case STANDARD:
            case FAMILY:
                int futureMonth = 3 + today.getMonth();
                if (futureMonth > 12){
                    futureMonth = futureMonth - 12;
                    return new Date(futureMonth, today.getDay(), today.getYear() + 1);
                }
                return new Date(futureMonth, today.getDay(), today.getYear());
            case PREMIUM:
                return new Date(today.getMonth(), today.getDay(), today.getYear() + 1);
        }
        return null;
    }
}