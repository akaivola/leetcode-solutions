package leetcode.merge_alternately_1768;

import org.junit.Test;

import static leetcode.merge_alternately_1768.MergeAlternately.mergeAlternately;
import static org.junit.Assert.assertEquals;

public class MergeAlternatelyTest {
    @Test
    public void testMergeAlternately() {
        assertEquals("apbqcr", mergeAlternately("abc", "pqr"));
        assertEquals("apbqcd", mergeAlternately("abcd", "pq"));
        assertEquals("apbqrs", mergeAlternately("ab", "pqrs"));
    }
}