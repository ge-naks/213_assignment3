package club;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit test class for testing the Date class.
 *
 * @author George W Nakhla
 *
 */
public class DateTest {

    /**
     * Test case for a date falling on February 29th in a non-leap year.
     */
    @Test
    public void testNonLeapYear() {
        Date test1 = new Date("2/29/2011");
        assertFalse(test1.isValid());
    }

    /**
     * Test case for a date with a month value of zero.
     */
    @Test
    public void testZeroMonth() {
        Date test2 = new Date("0/24/2014");
        assertFalse(test2.isValid());
    }

    /**
     * Test case for a date before the year 1900.
     */
    @Test
    public void testBefore1900() {
        Date test3 = new Date("4/2/1880");
        assertFalse(test3.isValid());
    }

    /**
     * Test case for a date with a day value that is out of bounds for the given month.
     */
    @Test
    public void testOutOfBoundsDay() {
        Date test4 = new Date("4/31/2011");
        assertFalse(test4.isValid());
    }

    /**
     * Test case for a date with a month value that is out of bounds.
     */
    @Test
    public void testOutOfBoundsMonth() {
        Date test5 = new Date("13/29/2011");
        assertFalse(test5.isValid());
    }

    /**
     * Test case for a date falling on February 29th in a valid leap year.
     */
    @Test
    public void testValidLeapYear() {
        Date test6 = new Date("2/29/2016");
        assertTrue(test6.isValid());
    }

    /**
     * Test case for a "regular" date falling within the valid range.
     */
    @Test
    public void testRegularDate() {
        Date test7 = new Date("12/14/2003");
        assertTrue(test7.isValid());
    }
}
