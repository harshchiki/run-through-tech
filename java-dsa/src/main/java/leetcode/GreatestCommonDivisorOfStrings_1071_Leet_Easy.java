package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/greatest-common-divisor-of-strings/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
 * <p>
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: str1 = "ABCABC", str2 = "ABC"
 * Output: "ABC"
 * Example 2:
 * <p>
 * Input: str1 = "ABABAB", str2 = "ABAB"
 * Output: "AB"
 * Example 3:
 * <p>
 * Input: str1 = "LEET", str2 = "CODE"
 * Output: ""
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of English uppercase letters.
 */
public class GreatestCommonDivisorOfStrings_1071_Leet_Easy {
    public static void main(String[] args) {
        String s1 = "ABCABC";
        String s2 = "ABC";
        System.out.println("GCD of " + s1 + " and " + s2 + " = " + "\"" + new GreatestCommonDivisorOfStrings_1071_Leet_Easy()
            .gcdOfStrings(s1, s2) + "\""); // expected output: "ABC"

        s1 = "ABABAB";
        s2 = "AB";
        System.out.println("GCD of " + s1 + " and " + s2 + " = " + "\"" + new GreatestCommonDivisorOfStrings_1071_Leet_Easy()
            .gcdOfStrings(s1,s2) + "\""); // expected output: "AB"

        s1 = "LEET";
        s2 = "CODE";
        System.out.println("GCD of " + s1 + " and " + s2 + " = " + "\"" + new GreatestCommonDivisorOfStrings_1071_Leet_Easy()
            .gcdOfStrings(s1, s2) + "\""); // expected output: ""

        s1 = "ABABABAB";
        s2 = "ABAB";
        System.out.println("GCD of " + s1 + " and " + s2 + " = " + "\"" + new GreatestCommonDivisorOfStrings_1071_Leet_Easy()
            .gcdOfStrings(s1, s2) + "\""); // expected output: "ABAB"

        s1 = "TAUXXTAUXXTAUXXTAUXXTAUXX";
        s2 = "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX";
        System.out.println("GCD of " + s1 + " and " + s2 + " = " + "\"" + new GreatestCommonDivisorOfStrings_1071_Leet_Easy()
            .gcdOfStrings(s1, s2) + "\""); // expected output: TAUXX
    }

    String gcdOfStrings(String str1, String str2) {
        List<String> divisorLst1 = getDivisor(str1);
        List<String> divisorLst2 = getDivisor(str2);

        String gcd = "";

        for(String s : divisorLst1) {
            if(divisorLst2.contains(s)) {
                gcd = s.length() > gcd.length() ? s : gcd;
            }
        }

        for(String s : divisorLst2) {
            if(divisorLst1.contains(s)) {
                gcd = s.length() > gcd.length() ? s : gcd;
            }
        }

        return gcd;
    }

    List<String> getDivisor(String s) {
        int n = s.length();

        String divisor = "";
        List<String> divisors = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            String seq = s.substring(0, i);
            boolean found = true;
            int j = i;
            for (; (j + i) <= n; j += i) {
                String substring = s.substring(j, j + i);
                if (!substring.equals(seq)) {
                    found = false;
                    break;
                }
            }
            if (j == n && found == true) {
                divisors.add(seq);
            }
        }
        divisors.add(s);
        return divisors;
    }
}
