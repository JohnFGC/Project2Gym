package fitnessmanagertests;

import fitnessmanager.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * FitnessClassTest is a JUnit class to test the checkIn, checkOut, checkInGuest, checkOutGuest methods in the FitnessClass class
 * It runs through several tests for checking the types of members allowed in and for duplicates (dupes) of members
 * @author Arya Shetty, John Greaney-Cheng
 */
public class FitnessClassTest {
    /**
     * Checks in Standard members into an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand, just makes sure there are no dupes
     */
    @Test
    public void test_Standard_Members_Checking_In_True() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Member p1 = new Member(m1FirstNameCase1[i], m1LastNameCase1[i]);
            assertTrue(fc.checkIn(p1));
        }
    }

    /**
     * Checks in Family members into an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand, just makes sure there are no dupes
     */
    @Test
    public void test_Family_Members_Checking_In_True() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Family p1 = new Family(m1FirstNameCase1[i], m1LastNameCase1[i]);
            assertTrue(fc.checkIn(p1));
        }
    }

    /**
     * Checks in Premium members into an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand, just makes sure there are no dupes
     */
    @Test
    public void test_Premium_Members_Checking_In_True() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Premium p1 = new Premium(m1FirstNameCase1[i], m1LastNameCase1[i]);
            assertTrue(fc.checkIn(p1));
        }
    }

    /**
     * Checks in any members into an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand, just makes sure there are no dupes,
     * but this one fails since there are
     */
    @Test
    public void test_Any_Member_Checking_In_For_Dupes_False() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        String m1DOBCase1[]= {"1/02/1999", "1/02/1950", "2/02/1999", "3/02/1949", "5/02/2000"};
        String m1FirstNameCase2[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase2[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        String m1DOBCase2[]= {"1/02/1999", "1/02/1950", "2/02/1999", "3/02/1949", "5/02/2000"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Member p1 = new Member(m1FirstNameCase1[i], m1LastNameCase1[i], m1DOBCase1[i]);
            Member p2 = new Member(m1FirstNameCase1[i], m1LastNameCase1[i], m1DOBCase2[i]);
            fc.checkIn(p1);
            assertFalse(fc.checkIn(p2));
        }
    }

    /**
     * Checks Out Standard members out of an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand
     */
    @Test
    public void test_Standard_Members_Checking_Out_True() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        String m1DOBCase1[]= {"1/02/1999", "1/02/1950", "2/02/1999", "3/02/1949", "5/02/2000"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Member p1 = new Member(m1FirstNameCase1[i], m1LastNameCase1[i], m1DOBCase1[i]);
            fc.checkIn(p1);
            assertTrue(fc.checkOut(p1));
        }
    }

    /**
     * Checks Out Family members out of an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand
     */
    @Test
    public void test_Family_Members_Checking_Out_True() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        String m1DOBCase1[]= {"1/02/1999", "1/02/1950", "2/02/1999", "3/02/1949", "5/02/2000"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Family p1 = new Family(m1FirstNameCase1[i], m1LastNameCase1[i], m1DOBCase1[i]);
            fc.checkIn(p1);
            assertTrue(fc.checkOut(p1));
        }
    }

    /**
     * Checks Out Premium members into an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand
     */
    @Test
    public void test_Premium_Members_Checking_Out_True() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        String m1DOBCase1[]= {"1/02/1999", "1/02/1950", "2/02/1999", "3/02/1949", "5/02/2000"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Premium p1 = new Premium(m1FirstNameCase1[i], m1LastNameCase1[i], m1DOBCase1[i]);
            fc.checkIn(p1);
            assertTrue(fc.checkOut(p1));
        }
    }

    /**
     * Checks Out Family members GUESTS out of an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand
     */
    @Test
    public void test_Family_Members_Guests_Checking_Out_True() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        String m1DOBCase1[]= {"1/02/1999", "1/02/1950", "2/02/1999", "3/02/1949", "5/02/2000"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Family p1 = new Family(m1FirstNameCase1[i], m1LastNameCase1[i], m1DOBCase1[i]);
            fc.checkInGuest(p1);
            assertTrue(fc.checkOutGuest(p1));
        }
    }

    /**
     * Checks Out Premium members GUESTS out of an arraylist stored in FitnessClass
     * This runs after all prerequisites are checked beforehand
     */
    @Test
    public void test_Premium_Members_Guests_Checking_Out_True() {
        FitnessClass fc = new FitnessClass("SPINNING","KIM", "EDISON");
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        String m1DOBCase1[]= {"1/02/1999", "1/02/1950", "2/02/1999", "3/02/1949", "5/02/2000"};

        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Premium p1 = new Premium(m1FirstNameCase1[i], m1LastNameCase1[i], m1DOBCase1[i]);
            fc.checkInGuest(p1);
            assertTrue(fc.checkOutGuest(p1));
        }
    }
}