package leetcode.dailytemperatures_739;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class DailyTemperaturesTest {
    @Test
    public void testDailyTemperatures() {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = {1, 1, 4, 2, 1, 1, 0, 0};
        int[] result = DailyTemperatures.dailyTemperatures(temperatures);
        assertArrayEquals(expected, result);
    }
}