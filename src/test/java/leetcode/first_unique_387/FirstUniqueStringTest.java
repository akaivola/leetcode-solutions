package leetcode.first_unique_387;

import junit.framework.TestCase;
import org.junit.Test;

// 387. First Unique Character in a String - simple test for local solution smoke testing
public class FirstUniqueStringTest {
    @Test
    public void testFirstUniqChar() {
        FirstUniqueString fus = new FirstUniqueString();
        String s = "leetcode";
        int result = fus.firstUniqChar(s);
        int expected = 0;
        TestCase.assertEquals(expected, result);
    }

    @Test
    public void testFirstUniqChar2() {
        FirstUniqueString fus = new FirstUniqueString();
        String s = "loveleetcode";
        int result = fus.firstUniqChar(s);
        int expected = 2;
        TestCase.assertEquals(expected, result);
    }
}