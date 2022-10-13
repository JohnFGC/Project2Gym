package fitnessmanager;

/**
 * Member Class is a blueprint for member objects
 * Stores the following member data:
 *  - First and Last name
 *  - Date of Birth
 *  - Date of Membership Expiration
 *  - Location of Gym Membership
 * @author Arya Shetty, John Greaney-Cheng
 */
public class Member implements Comparable<Member>{
    protected String fname;
    protected String lname;
    protected Date dob;
    protected Date expire;
    protected Location location;

    /**
     * Creates a member object
     * @param fname first name of the member
     * @param lname last name of the member
     * @param dob birthday of the member
     * @param expire expiration date of the member's gym membership
     * @param location the gym the member belongs to
     */
    public Member(String fname, String lname, String dob, String expire, String location){
        this.fname = fname;
        this.lname = lname;
        this.dob = new Date(dob);
        this.expire = new Date(expire);
        this.location = Location.stringToLocation(location);
    }

    /**
     * Creates a member object
     * @param fname first name of the member
     * @param lname last name of the member
     * @param dob birthday of the member
     * @param expire expiration date of the member's gym membership
     * @param location the gym the member belongs to
     */
    public Member(String fname, String lname, String dob, Date expire, String location){
        this.fname = fname;
        this.lname = lname;
        this.dob = new Date(dob);
        this.expire = expire;
        this.location = Location.stringToLocation(location);
    }

    /**
     * Creates a member object
     * @param fname first name of the member
     * @param lname last name of the member
     * @param dob birthday of the member
     */
    public Member(String fname, String lname, String dob){
        this.fname = fname;
        this.lname = lname;
        this.dob = new Date(dob);
    }

    /**
     * Creates a member object
     * @param fname first name of the member
     * @param lname last name of the member
     */
    public Member(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
    }

    public double membershipFee(){
        return (39.99 * 3) + 29.99;
    }

    /**
     * Returns String representation of this member
     * Format ex: April March, DOB: 3/31/1990, Membership expires 6/30/2023,
     *            Location: PISCATAWAY, 08854, MIDDLESEX
     * @return String representation of this member
     */
    @Override
    public String toString(){
        if (this.expire.compareTo(new Date()) < 0){                  //Check if membership expiration date has passed
            return this.fname + " " + this.lname + ", DOB: " + this.dob.toString()
                              +  ", Membership expired " + this.expire.toString()
                              +  ", Location: " + this.location.toString();
        }
        return this.fname + " " + this.lname + ", DOB: " + this.dob.toString()
                          +  ", Membership expires " + this.expire.toString()
                          +  ", Location: " + this.location.toString();
    }

    /**
     * Determine equality between this member and another
     * Two members are equal if
     *  - They have the same first name
     *  - They have the same last name
     *  - They have the same birthday
     * Names are not case-sensitive
     * @param obj the member to check equality with
     * @return true if members are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Member){
            Member member = (Member) obj;
            if (this.fname.equalsIgnoreCase(member.getFname())
                    && this.lname.equalsIgnoreCase(member.getLname())
                    && this.dob.compareTo(member.getDob()) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Compares the name of this member to another
     * Format: m1.compareTo(m2)
     * First compare last names and if they're the
     * same, compare first names
     * Note: Names are not case-sensitive when compared
     * to one another, all comparisons are done with
     * names converted to lowercase
     * @param member the member to compare names with (m2)
     * @return >0 if m2's name is greater lexicographically,
     *         <0 if m2's name is lesser lexicographically,
     *         =0 if m2's name is same as this member
     */
    @Override
    public int compareTo(Member member){
        if (this.lname.compareToIgnoreCase(member.getLname()) != 0){
            return this.lname.compareToIgnoreCase(member.getLname());
        }
        return this.fname.compareToIgnoreCase(member.getFname());
    }

    /**
     * Getter method for member's first name
     * @return member's first name
     */
    public String getFname(){
        return this.fname;
    }

    /**
     * Getter method for member's last name
     * @return member's last name
     */
    public String getLname(){
        return this.lname;
    }

    /**
     * Getter method for member's birthday
     * @return member's birthday
     */
    public Date getDob(){
        return this.dob;
    }

    /**
     * Getter method for member's gym membership expiration date
     * @return member's gym membership expiration date
     */
    public Date getExpire(){
        return this.expire;
    }

    /**
     * Getter method for member's gym membership location
     * @return member's gym membership location
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * Testbed main to test the compareTo method
     * @param args standard paramater for main
     */
    public static void main(String[] args){
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        String m2FirstNameCase1[]= {"April", "Mary", "Duke", "Kate", "Paul"};
        String m2LastNameCase1[]= {"March", "Lindsey", "Ellington", "Lindsey", "Siegel"};
        callTests(m1FirstNameCase1, m1LastNameCase1, m2FirstNameCase1, m2LastNameCase1, " < 0", 1);

        String m1FirstNameCase2[]= {"Mary", "Jane", "Duke", "Kate", "Paul"};
        String m1LastNameCase2[]= {"Lindsey", "Doe", "Ellington", "Lindsey", "Siegel"};
        String m2FirstNameCase2[]= {"John", "Roy", "Carl", "John", "April"};
        String m2LastNameCase2[]= {"Doe", "Brooks", "Brown", "Doe", "March"};
        callTests(m1FirstNameCase2, m1LastNameCase2, m2FirstNameCase2, m2LastNameCase2, " > 0", 2);

        String m1FirstNameCase3[]= {"Jane", "Kate", "Bruce", "Carl", "Adrian"};
        String m1LastNameCase3[]= {"Doe", "Lindsey", "Ellington", "Brown", "Siegel"};
        String m2FirstNameCase3[]= {"John", "Mary", "Duke", "Paul", "Paul"};
        String m2LastNameCase3[]= {"Doe", "Lindsey", "Ellington", "Brown", "Siegel"};
        callTests(m1FirstNameCase3, m1LastNameCase3, m2FirstNameCase3, m2LastNameCase3, " < 0", 3);

        String m1FirstNameCase4[]= {"John", "Mary", "Duke", "Paul", "Paul"};
        String m1LastNameCase4[]= {"Doe", "Lindsey", "Ellington", "Brown", "Siegel"};
        String m2FirstNameCase4[]= {"Jane", "Kate", "Bruce", "Carl", "Adrian"};
        String m2LastNameCase4[]= {"Doe", "Lindsey", "Ellington", "Brown", "Siegel"};
        callTests(m1FirstNameCase4, m1LastNameCase4, m2FirstNameCase4, m2LastNameCase4, " > 0", 4);

        String m1FirstNameCase5[]= {"John", "Mary", "Duke"};
        String m1LastNameCase5[]= {"Doe", "Lindsey", "Ellington"};
        String m2FirstNameCase5[]= {"john", "Mary", "Duke"};
        String m2LastNameCase5[]= {"Doe", "Lindsey", "Ellington"};
        callTests(m1FirstNameCase5, m1LastNameCase5, m2FirstNameCase5, m2LastNameCase5, " = 0", 5);
    }

    /**
     * Helper Method for Testbed Main
     * Tests every pair of members individually, outputs
     * if pair PASSed or FAILed expectations
     * m1FirstName[0], m1LastName[0], m2FirstName[0], m2LastName[0],
     * all hold instance variables about the same member pair
     * @param m1FirstName array of m1's first name to test
     * @param m1LastName array of m1's last name to test
     * @param m2FirstName array of m2's first name to test
     * @param m2LastName array of m2's last name to test
     * @param expectedResult expectedResult of the test case
     * @param testCase the number testcase in the corresponding
     *                 test design document
     */
    private static void callTests(String[] m1FirstName, String[] m1LastName, String[] m2FirstName,
                                  String[] m2LastName, String expectedResult, int testCase){
        for (int i = 0; i < m1FirstName.length; i++){
            Member m1 = new Member(m1FirstName[i], m1LastName[i]);
            Member m2 = new Member(m2FirstName[i], m2LastName[i]);
            System.out.println("Test Case #" + testCase + ", m1: " + m1FirstName[i]
                    + " " + m1LastName[i] + ", m2: " + m2FirstName[i]
                    + " " + m2LastName[i] + ".");
            switch (expectedResult){
                case " > 0":
                    if (m1.compareTo(m2) > 0){
                        System.out.println("m1.compareTo(m2) returns" + expectedResult);
                        System.out.println("PASS");
                    }
                    else {
                        System.out.println("m1.compareTo(m2) returns not" + expectedResult);
                        System.out.println("FAIL");
                    }
                    break;
                case " < 0":
                    if (m1.compareTo(m2) < 0){
                        System.out.println("m1.compareTo(m2) returns" + expectedResult);
                        System.out.println("PASS");
                    }
                    else {
                        System.out.println("m1.compareTo(m2) returns not" + expectedResult);
                        System.out.println("FAIL");
                    }
                    break;
                case " = 0":
                    if (m1.compareTo(m2) == 0){
                        System.out.println("m1.compareTo(m2) returns" + expectedResult);
                        System.out.println("PASS");
                    }
                    else {
                        System.out.println("m1.compareTo(m2) returns not" + expectedResult);
                        System.out.println("FAIL");
                    }
            }
        }
    }
}
