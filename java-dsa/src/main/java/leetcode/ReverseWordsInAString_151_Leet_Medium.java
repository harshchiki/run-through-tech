package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/description/?envType=study-plan-v2&envId=leetcode-75
 * Given an input string s, reverse the order of the words.
 * <p>
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * <p>
 * Return a string of the words in reverse order concatenated by a single space.
 * <p>
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 * Example 2:
 * <p>
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 * Example 3:
 * <p>
 * Input: s = "a good   example"
 * Output: "example good a"
 * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s contains English letters (upper-case and lower-case), digits, and spaces ' '.
 * There is at least one word in s.
 * <p>
 * <p>
 * Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */
public class ReverseWordsInAString_151_Leet_Medium {
    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println("Reverse of \"" + s + "\" = \"" + new ReverseWordsInAString_151_Leet_Medium().reverseWords(s) + "\"");

        s = "  hello world  ";
        System.out.println("Reverse of \"" + s + "\" = \"" + new ReverseWordsInAString_151_Leet_Medium().reverseWords(s) + "\"");

        s = "a good   example";
        System.out.println("Reverse of \"" + s + "\" = \"" + new ReverseWordsInAString_151_Leet_Medium().reverseWords(s) + "\"");
    }

    public String reverseWords(String s) {
        List<String> words = new ArrayList<>();
        s = s.trim();
        int n = s.length();
        for (int i = 0; i < n; ) {
            for (int j = i; j < n; j++) {
                char charThisPosition = s.charAt(j);
                if (charThisPosition == ' ' || j == (n - 1)) {
                    String thisWord = (s.substring(i, (j == (n - 1)) ? j + 1 : j)).trim();
                    if(thisWord.length() > 0) {
                        words.add(thisWord);
                    }
                    i = j + 1;
                    break;
                }
            }
        }

        StringBuilder reverse = new StringBuilder();
        for (int i = words.size() - 1; i >= 0; i--) {
            reverse.append(words.get(i));
            if(i != 0) {
                reverse.append(' ');
            }
        }

        return reverse.toString();
    }
}
