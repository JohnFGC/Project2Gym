package fitnessmanagertests;

import fitnessmanager.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * PremiumTest is a JUnit class to test the membershipFee in the Premium class
 * It runs through several tests for checking the types of members and what fees they have and are equal to
 * @author Arya Shetty, John Greaney-Cheng
 */
public class PremiumTest {

    /**
     * The members here are premium
     * They take the membershipfee method from Premium and output $659.89
     */
    @Test
    public void test_Premium_Members_Have_Premium_Fee_True() {
        String m1FirstNameCase1[]= {"John", "Duke", "Roy", "Carl", "Bill"};
        String m1LastNameCase1[]= {"Doe", "Ellington", "Brooks", "Brown", "Scanlan"};
        for (int i = 0; i < m1FirstNameCase1.length; i++) {
            Premium p1 = new Premium(m1FirstNameCase1[i], m1LastNameCase1[i]);
            assertEquals(Fee.FAMILY_PER_MONTH.getPrice() * (Constant.ANNUALLY_NUM_MONTHS.getValue() - 1), p1.membershipFee(), 0);
        }
    }

    /**
     * The members here are premium, but parent type
     * They take the membershipfee method from Premium and output $659.89
     */
    @Test
    public void test_Premium_Members_Of_Type_Member_Have_Premium_Fee_True() {
        String m2FirstNameCase1[]= {"April", "Mary", "Duke", "Kate", "Paul"};
        String m2LastNameCase1[]= {"March", "Lindsey", "Ellington", "Lindsey", "Siegel"};
        for (int i = 0; i < m2FirstNameCase1.length; i++) {
            Member p2 = new Premium(m2FirstNameCase1[i], m2LastNameCase1[i]);
            assertEquals(Fee.FAMILY_PER_MONTH.getPrice() * (Constant.ANNUALLY_NUM_MONTHS.getValue() - 1), p2.membershipFee(), 0);
        }
    }

    /**
     * The members are of family membership
     * Family doesn't take the membershipfee method from Premium and the output is not $659.89 but $209.96
     */
    @Test
    public void test_Family_Members_No_Have_Premium_Fee_True() {
        String m1FirstNameCase2[]= {"Mary", "Jane", "Duke", "Kate", "Paul"};
        String m1LastNameCase2[]= {"Lindsey", "Doe", "Ellington", "Lindsey", "Siegel"};
        for (int i = 0; i < m1FirstNameCase2.length; i++) {
            Family p1 = new Family(m1FirstNameCase2[i], m1LastNameCase2[i]);
            assertEquals(((Fee.FAMILY_PER_MONTH.getPrice() * Constant.QUARTERLY_NUM_MONTHS.getValue())
                    + Fee.ONE_TIME_FEE.getPrice()), p1.membershipFee(),0);
        }
    }

    /**
     * The members here are Premium, but type Family
     * The members take the membershipfee method from Premium and the output is $659.89
     */
    @Test
    public void test_Premium_Members_Of_Type_Family_Have_Premium_Fee_True() {
        String m2FirstNameCase2[]= {"John", "Roy", "Carl", "John", "April"};
        String m2LastNameCase2[]= {"Doe", "Brooks", "Brown", "Doe", "March"};
        for (int i = 0; i < m2FirstNameCase2.length; i++) {
            Family p2 = new Premium(m2FirstNameCase2[i], m2LastNameCase2[i]);
            assertEquals(Fee.FAMILY_PER_MONTH.getPrice() * (Constant.ANNUALLY_NUM_MONTHS.getValue() - 1), p2.membershipFee(), 0);
        }
    }

    /**
     * The members have standard membership,
     * Standard doesn't take the membershipfee method from Premium and the output is not $659.89 but $149.96
     */
    @Test
    public void test_Standard_Members_No_Have_Premium_Fee_True() {
        String m1FirstNameCase3[]= {"Jane", "Kate", "Bruce", "Carl", "Adrian"};
        String m1LastNameCase3[]= {"Doe", "Lindsey", "Ellington", "Brown", "Siegel"};
        for (int i = 0; i < m1FirstNameCase3.length; i++) {
            Member p1 = new Member(m1FirstNameCase3[i], m1LastNameCase3[i]);
            assertEquals((Fee.STANDARD_PER_MONTH.getPrice() * Constant.QUARTERLY_NUM_MONTHS.getValue())
                    + Fee.ONE_TIME_FEE.getPrice(), p1.membershipFee(), 0);
        }
    }
}