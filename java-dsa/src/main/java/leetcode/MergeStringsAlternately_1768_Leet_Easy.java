package leetcode;

/**
 * https://leetcode.com/problems/merge-strings-alternately/?envType=study-plan-v2&envId=leetcode-75
 *
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.
 *
 * Return the merged string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * Explanation: The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 * Example 2:
 *
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 * Example 3:
 *
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 * Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 * word1:  a   b   c   d
 * word2:    p   q
 * merged: a p b q c   d
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 100
 * word1 and word2 consist of lowercase English letters.
 */
public class MergeStringsAlternately_1768_Leet_Easy {
    public static void main(String[] args) {
        System.out.println(new MergeStringsAlternately_1768_Leet_Easy().mergeAlternately("ab", "pqrs"));
    }
    public String mergeAlternately(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int i1 = 0, i2 = 0;
        StringBuilder finalStrBuilder = new StringBuilder();
        boolean firstList = true;
        while (i1 < n1 && i2 < n2) {
            if(firstList) {
                finalStrBuilder.append(word1.charAt(i1++));
            } else {
                finalStrBuilder.append(word2.charAt(i2++));
            }
            firstList = !firstList;
        }

        if (i1 < n1) {
            finalStrBuilder.append(word1.substring(i1, n1));
        }

        if (i2 < n2) {
            finalStrBuilder.append(word2.substring(i2, n2));
        }

        return finalStrBuilder.toString();
    }
}
