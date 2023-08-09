package easy;

import java.util.*;

/**
 * Given 2 non empty array of ints - find if second is a continuous subsequence of the first
 */
public class ValidateContinuousSubsequence {
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

        int firstLength = array.size(), secondLength = sequence.size();
        int firstPointer = 0 , secondPointer = 0;

        while(firstPointer < firstLength && secondPointer < secondLength) {
            if(array.get(firstPointer) == sequence.get(secondPointer)) {
                secondPointer++;
            }
            firstPointer++;
        }

        return secondPointer == secondLength;
    }
}
