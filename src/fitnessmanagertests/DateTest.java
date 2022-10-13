package fitnessmanagertests;

import fitnessmanager.Date;

import static org.junit.Assert.*;

public class DateTest {

    @org.junit.Test
    //Valid range for the month shall be 1-12
    public void test_days_in_valid_month_range() {
        int monthTestCase1[] = {13, -1, 13, 100, 0, -12, 20};
        int dayTestCase1[] = {8, 31, 31, 0, -35, 2, -20};
        int yearTestCase1[] = {1977, 2003, 2003, 0, 2, 5060, -20};

        for (int i = 0; i < monthTestCase1.length; i++) {
            Date date = new Date(monthTestCase1[i], dayTestCase1[i], yearTestCase1[i]);
            assertFalse(date.isValid());
        }
    }

    //Valid range for the day shall be 1-31 for the following numbered months: (1, 3, 5, 7, 8, 10, 12)
    public void test_days_in_valid_day_range() {
        int monthTestCase2[]= {3, 12, 1, 5, 8, 10, 7};
        int dayTestCase2[]= {32, 32, 0, 100, -20, -2, 40};
        int yearTestCase2[]= {2003, 1989, 2500, 2000, 1000, 2, 2011};

        for (int i = 0; i < monthTestCase2.length; i++) {
            Date date = new Date(monthTestCase2[i], dayTestCase2[i], yearTestCase2[i]);
            assertFalse(date.isValid());
        }
    }
}