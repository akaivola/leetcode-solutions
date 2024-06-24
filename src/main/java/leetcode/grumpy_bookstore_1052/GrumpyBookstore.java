package leetcode.grumpy_bookstore_1052;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

// 1052. Grumpy Bookstore Owner
public class GrumpyBookstore {
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        List<Deque<Integer>> tricks = new ArrayList<>();
        Deque<Integer> last = new ArrayDeque<>();
        var max = 0;

        var sum = 0;
        for (int i = 0; i < customers.length; i++) {
            var customer = customers[i];
            var isGrumpy = grumpy[i] > 0;
            sum += isGrumpy ? 0 : customer;
            if (last.size() == minutes)
                last.pop();

            last.add(isGrumpy ? customer : 0);
            if (isGrumpy) {
                var adder = new LongAdder();
                last.parallelStream().forEach(adder::add);
                max = Math.max(max, adder.intValue());
            }
        }

        return sum + max;
    }
}