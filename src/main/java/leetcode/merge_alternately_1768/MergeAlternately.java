package leetcode.merge_alternately_1768;

public class MergeAlternately {
    // 1768. Merge Strings Alternately
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        var word1OrWord2 = word1.length() <= word2.length();

        String wordA = word1OrWord2 ? word1 : word2;
        String wordB = word1OrWord2 ? word2 : word1;
        int i = 0;

        for (; i < wordA.length(); i++) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
        }
        return sb.append(wordB.substring(i)).toString();
    }
}