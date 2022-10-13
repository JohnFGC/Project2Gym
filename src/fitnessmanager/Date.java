package fitnessmanager;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Date Class is a blueprint for date objects
 * Stores the month, day, and year of date
 * @author Arya Shetty, John Greaney-Cheng
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    /**
     * Creates a date object where the date is today
     */
    public Date(){
        Calendar calendar = Calendar.getInstance();
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DATE);
        this.year = calendar.get(Calendar.YEAR);
    }

    /**
     * Creates a date object
     * Input Format: 1/02/2003
     *  - Month is 1 (January)
     *  - Day is 2
     *  - Year is 2003
     * @param date the date being stored as a String
     */
    public Date(String date){
        StringTokenizer splitDate = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(splitDate.nextToken());
        this.day = Integer.parseInt(splitDate.nextToken());
        this.year = Integer.parseInt(splitDate.nextToken());
    }

    /**
     * Creates a date object
     * @param month the month of the date
     * @param day the day of the date
     * @param year the year of the date
     */
    public Date(int month, int day, int year){
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /**
     * Compare this date to another
     * Format: d1.compareTo(d2)
     * @param date the date that this date is being compared to (d2)
     * @return 1 if d2's date is before this date,
     *         -1 if d2's date is after this date,
     *         0 if dates are the same
     */
    @Override
    public int compareTo(Date date){
        if (this.year < date.getYear()){
            return -1;
        }
        if (this.year > date.getYear()){
            return 1;
        }
        if (this.month < date.getMonth()){
            return -1;
        }
        if (this.month > date.getMonth()){
            return 1;
        }
        if (this.day < date.getDay()){
            return -1;
        }
        if (this.day > date.getDay()){
            return 1;
        }
        return 0;
    }

    /**
     * Returns String representation of this date
     * @return String representation of this date
     */
    @Override
    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Checks if date exists in the calendar
     * Based on the month, if the day is between the first and the last
     * day of the month, the date exists in the calendar.
     * This method assumes February has 28 days then checks
     * if the date is leap day on a leap year
     * @return true if date exists in the calendar, false otherwise
     */
    public boolean isValid(){
        Month month = Month.intToMonth(this.month);
        if (month == null){
            return false;
        }
        if (this.day >= Constant.FIRST_DAY_OF_MONTH.getValue()
                && this.day <= month.getLastDayInMonth()){
            return true;
        }
        if (month == Month.FEBRUARY
                && this.day == Constant.LEAP_DAY.getValue()
                && isLeapYear()){
            return true;
        }
        return false;
    }

    /**
     * Checks if this year is a leap year
     * If the year is not evenly divisible by 4, it's not a leap year
     * If the year is evenly divisible by 4, but not by 100, it's a leap year
     * If the year is evenly divisible by 100, but not by 400, it's not a leap year
     * If the year is evenly divisible by 400, it's a leap year
     * @return true if this year is a leap year, false otherwise
     */
    private boolean isLeapYear(){
        if (this.year % Constant.QUADRENNIAL.getValue() == 0){
            if (this.year % Constant.CENTENNIAL.getValue() == 0){
                if (this.year % Constant.QUARTERCENTENNIAL.getValue() == 0){
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Getter method for year of date
     * @return date's year
     */
    public int getYear(){
        return this.year;
    }

    /**
     * Getter method for month of date
     * @return date's month
     */
    public int getMonth(){
        return this.month;
    }

    /**
     * Getter method for day of date
     * @return date's day
     */
    public int getDay(){
        return this.day;
    }

    /**
     * Testbed main to test the isValid method
     * @param args standard paramater for main
     */
    public static void main(String[] args){
        int monthTestCase1[]= {13, -1, 13, 100, 0, -12, 20};
        int dayTestCase1[]= {8, 31, 31, 0, -35, 2, -20};
        int yearTestCase1[]= {1977, 2003, 2003, 0, 2, 5060, -20};
        callTests(monthTestCase1, dayTestCase1, yearTestCase1, false, 1);

        int monthTestCase2[]= {3, 12, 1, 5, 8, 10, 7};
        int dayTestCase2[]= {32, 32, 0, 100, -20, -2, 40};
        int yearTestCase2[]= {2003, 1989, 2500, 2000, 1000, 2, 2011};
        callTests(monthTestCase2, dayTestCase2, yearTestCase2, false, 2);

        int monthTestCase3[]= {4, 9, 6, 9, 11};
        int dayTestCase3[]= {31, 33, 32, 31, 31};
        int yearTestCase3[]= {2003, 2004, 2009, 2000, 2004};
        callTests(monthTestCase3, dayTestCase3, yearTestCase3, false, 3);

        int monthTestCase4[]= {2, 2, 2, 2, 2};
        int dayTestCase4[]= {30, 2011, -10, 0, -5};
        int yearTestCase4[]= {2011, 30, 22, 2010, 2000};
        callTests(monthTestCase4, dayTestCase4, yearTestCase4, false, 4);

        int monthTestCase5[]= {2, 2, 2, 2, 2};
        int dayTestCase5[]= {29, 29, 29, 29, 29};
        int yearTestCase5[]= {2003, 2100, 1999, 2019, 2014};
        callTests(monthTestCase5, dayTestCase5, yearTestCase5, false, 5);

        int monthTestCase6[]= {3, 12, 1, 12, 5, 8, 1, 3, 5, 3, 1, 12, 5, 7, 12, 10, 3};
        int dayTestCase6[]= {30, 2, 20, 20, 1, 8, 20, 30, 1, 31, 31, 1, 31, 15, 31, 7, 31};
        int yearTestCase6[]= {2023, 2022, 2004, 2004, 1999, 1977, 2003, 2021, 1996, 2023, 2023, 1989, 2023, 1977, 2023, 1991, 1990};
        callTests(monthTestCase6, dayTestCase6, yearTestCase6, true, 6);

        int monthTestCase7[]= {4, 9, 6, 9, 11, 11, 6, 9, 9, 4, 6, 9, 9};
        int dayTestCase7[]= {10, 29, 30, 14, 30, 20, 30, 30, 2, 3, 30, 30, 9};
        int yearTestCase7[]= {2003, 2004, 2009, 2000, 2004, 2003, 1999, 2020, 2022, 2003, 2023, 2023, 1977};
        callTests(monthTestCase7, dayTestCase7, yearTestCase7, true, 7);

        int monthTestCase8[]= {2, 2, 2, 2, 2};
        int dayTestCase8[]= {28, 1, 3, 2, 15};
        int yearTestCase8[]= {2011, 30, 22, 2010, 2000};
        callTests(monthTestCase8, dayTestCase8, yearTestCase8, true, 8);

        int monthTestCase9[]= {2, 2, 2, 2, 2};
        int dayTestCase9[]= {29, 29, 29, 29, 29};
        int yearTestCase9[]= {2000, 2004, 2008, 1996, 2400};
        callTests(monthTestCase9, dayTestCase9, yearTestCase9, true, 9);
    }

    /**
     * Helper Method for Testbed Main
     * Tests every date individually, outputs
     * if date PASSed or FAILed expectations
     * month[0], day[0], year[0], all hold instance variables
     * of the same date
     * @param month array of months to test
     * @param day array of days to test
     * @param year array of years to test
     * @param expectedResult expectedResult of the test case
     * @param testCase the number testcase in the corresponding
     *                 test design document
     */
    private static void callTests(int[] month, int[] day, int[] year, boolean expectedResult, int testCase){
        for (int i = 0; i < month.length; i++){
            Date date = new Date(month[i], day[i], year[i]);
            System.out.println("Test Case #" + testCase + ", " + date.toString()
                                    + " isValid() returns " + date.isValid());
            if (date.isValid() == expectedResult){
                System.out.println("PASS");
            }
            else{
                System.out.println("FAIL");
            }
        }
    }
}
