package leetcode.dailytemperatures_739;

import java.util.ArrayDeque;
import java.util.Deque;

// 739. Daily Temperatures
public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        //Input: temperatures = [73,74,75,71,69,72,76,73]
        //Output: [1,1,4,2,1,1,0,0]
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[temperatures.length];
        res[temperatures.length - 1] = 0;
        stack.offer(temperatures.length - 1);

        for (int i = temperatures.length - 2; i >= 0; i--) {
            while (!stack.isEmpty()) {
                int temperature = temperatures[i];
                Integer lastIndex = stack.peekLast();
                int temperatureAtLastIndex = temperatures[lastIndex];
                if (temperatureAtLastIndex > temperature) break;
                stack.pollLast();
            }

            if (stack.isEmpty()) {
                res[i] = 0;
            } else {
                res[i] = stack.peekLast() - i;
            }

            stack.offer(i);
        }

        return res;
    }
}