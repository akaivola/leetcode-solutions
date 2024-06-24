package leetcode.first_unique_387;

import java.util.LinkedHashMap;

// 387. First Unique Character in a String
public class FirstUniqueString {
    public int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> freqs = new LinkedHashMap<>();
        for (Character c : s.toCharArray()) {
            freqs.put(c, freqs.getOrDefault(c, 0) + 1);
        }

        return freqs.entrySet().stream().dropWhile(entry -> entry.getValue() != 1).findFirst().map(
                entry -> s.indexOf(entry.getKey())).orElse(-1);
    }
}