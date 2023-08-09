package easy;

import java.util.*;

/**
 * Given 2 non empty array of ints - find if second is a continuous subsequence of the first
 */
public class ValidateSubsequence {
    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(new Integer[] {5, 1, 22, 25, 6, -1, 8, 10});
        List<Integer> sequence = Arrays.asList(new Integer[] {1, 6, -1, 10});
        System.out.println(isValidSubsequence(array, sequence));
    }

    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        if(array == null || sequence == null) {
            return false;
        }

        if(sequence.size() > array.size()) {
            return false;
        }
        return false;
    }
}
