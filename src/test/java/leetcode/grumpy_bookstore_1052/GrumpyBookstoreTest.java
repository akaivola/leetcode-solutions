package leetcode.grumpy_bookstore_1052;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static leetcode.grumpy_bookstore_1052.GrumpyBookstore.maxSatisfied;

// 1052. Grumpy Bookstore Owner - simple test for local solution smoke testing
public class GrumpyBookstoreTest {
    @Test
    public void testMaxSatisfied() {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int minutes = 3;
        int result = maxSatisfied(customers, grumpy, minutes);
        int expected = 16;
        assertEquals(expected, result);
    }
}