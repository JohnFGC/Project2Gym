package fitnessmanagertests;

import fitnessmanager.Date;
import static org.junit.Assert.*;

/**
 * DateTest is a JUnit class to test the isValid method in the Date class
 * It runs through several tests for checking the days, months, years, and even leap years
 * @author Arya Shetty, John Greaney-Cheng
 */
public class DateTest {

    @org.junit.Test
    /**
     * Tests valid range for the month which shall be 1-12,
     * in this case the months are not valid
     */
    public void test_days_in_valid_month_range_false() {
        int monthTestCase1[] = {13, -1, 13, 100, 0, -12, 20};
        int dayTestCase1[] = {8, 31, 31, 0, -35, 2, -20};
        int yearTestCase1[] = {1977, 2003, 2003, 0, 2, 5060, -20};

        for (int i = 0; i < monthTestCase1.length; i++) {
            Date date = new Date(monthTestCase1[i], dayTestCase1[i], yearTestCase1[i]);
            assertFalse(date.isValid());
        }
    }

    @org.junit.Test
    /**
     * Tests valid range for the day which shall be 1-31 for the following numbered months: (1, 3, 5, 7, 8, 10, 12),
     * in this case the days are not valid
     */
    public void test_days_in_valid_day_range_for_thirty_one_false() {
        int monthTestCase2[]= {3, 12, 1, 5, 8, 10, 7};
        int dayTestCase2[]= {32, 32, 0, 100, -20, -2, 40};
        int yearTestCase2[]= {2003, 1989, 2500, 2000, 1000, 2, 2011};

        for (int i = 0; i < monthTestCase2.length; i++) {
            Date date = new Date(monthTestCase2[i], dayTestCase2[i], yearTestCase2[i]);
            assertFalse(date.isValid());
        }
    }

    @org.junit.Test
    /**
     * Tests valid range for the day which shall be 1-30 for the following numbered months: (4, 6, 9, 11),
     * in this case the days are not valid
     */
    public void test_days_in_valid_day_range_for_thirty_false() {
        int monthTestCase3[]= {4, 9, 6, 9, 11};
        int dayTestCase3[]= {31, 33, 32, 31, 31};
        int yearTestCase3[]= {2003, 2004, 2009, 2000, 2004};

        for (int i = 0; i < monthTestCase3.length; i++) {
            Date date = new Date(monthTestCase3[i], dayTestCase3[i], yearTestCase3[i]);
            assertFalse(date.isValid());
        }
    }

    @org.junit.Test
    /**
     * Tests February (month = 2) which can’t have a day less than 1 nor more than 29,
     * in this case the days are not valid
     */
    public void test_days_in_valid_day_range_for_feb_false() {
        int monthTestCase4[]= {2, 2, 2, 2, 2};
        int dayTestCase4[]= {30, 2011, -10, 0, -5};
        int yearTestCase4[]= {2011, 30, 22, 2010, 2000};

        for (int i = 0; i < monthTestCase4.length; i++) {
            Date date = new Date(monthTestCase4[i], dayTestCase4[i], yearTestCase4[i]);
            assertFalse(date.isValid());
        }
    }

    @org.junit.Test
    /**
     * Tests that accepts the 29th of February only on leap years,
     * in this case the days are not valid
     */
    public void test_days_in_valid_leap_year_false() {
        int monthTestCase5[]= {2, 2, 2, 2, 2};
        int dayTestCase5[]= {29, 29, 29, 29, 29};
        int yearTestCase5[]= {2003, 2100, 1999, 2019, 2014};

        for (int i = 0; i < monthTestCase5.length; i++) {
            Date date = new Date(monthTestCase5[i], dayTestCase5[i], yearTestCase5[i]);
            assertFalse(date.isValid());
        }
    }

    @org.junit.Test
    /**
     * Tests valid range for the month which shall be 1-12,
     * in this case the months are valid
     */
    public void test_days_in_valid_month_range_true() {
        int monthTestCase6[]= {3, 12, 1, 12, 5, 8, 1, 3, 5, 3, 1, 12, 5, 7, 12, 10, 3};
        int dayTestCase6[]= {30, 2, 20, 20, 1, 8, 20, 30, 1, 31, 31, 1, 31, 15, 31, 7, 31};
        int yearTestCase6[]= {2023, 2022, 2004, 2004, 1999, 1977, 2003, 2021, 1996, 2023, 2023, 1989, 2023, 1977, 2023, 1991, 1990};

        for (int i = 0; i < monthTestCase6.length; i++) {
            Date date = new Date(monthTestCase6[i], dayTestCase6[i], yearTestCase6[i]);
            assertTrue(date.isValid());
        }
    }

    @org.junit.Test
    /**
     * Tests valid range for the day which shall be 1-31 for the following numbered months: (1, 3, 5, 7, 8, 10, 12),
     * in this case the days are valid
     */
    public void test_days_in_valid_day_range_for_thirty_true() {
        int monthTestCase7[]= {4, 9, 6, 9, 11, 11, 6, 9, 9, 4, 6, 9, 9};
        int dayTestCase7[]= {10, 29, 30, 14, 30, 20, 30, 30, 2, 3, 30, 30, 9};
        int yearTestCase7[]= {2003, 2004, 2009, 2000, 2004, 2003, 1999, 2020, 2022, 2003, 2023, 2023, 1977};

        for (int i = 0; i < monthTestCase7.length; i++) {
            Date date = new Date(monthTestCase7[i], dayTestCase7[i], yearTestCase7[i]);
            assertTrue(date.isValid());
        }
    }

    @org.junit.Test
    /**
     * Tests February (month = 2) which can’t have a day less than 1 nor more than 29,
     * in this case the days are valid
     */
    public void test_days_in_valid_day_range_for_feb_true() {
        int monthTestCase8[]= {2, 2, 2, 2, 2};
        int dayTestCase8[]= {28, 1, 3, 2, 15};
        int yearTestCase8[]= {2011, 30, 22, 2010, 2000};

        for (int i = 0; i < monthTestCase8.length; i++) {
            Date date = new Date(monthTestCase8[i], dayTestCase8[i], yearTestCase8[i]);
            assertTrue(date.isValid());
        }
    }

    @org.junit.Test
    /**
     * Tests that accepts the 29th of February only on leap years,
     * in this case the days are valid
     */
    public void test_days_in_valid_leap_year_true() {
        int monthTestCase9[]= {2, 2, 2, 2, 2};
        int dayTestCase9[]= {29, 29, 29, 29, 29};
        int yearTestCase9[]= {2000, 2004, 2008, 1996, 2400};

        for (int i = 0; i < monthTestCase9.length; i++) {
            Date date = new Date(monthTestCase9[i], dayTestCase9[i], yearTestCase9[i]);
            assertTrue(date.isValid());
        }
    }

}