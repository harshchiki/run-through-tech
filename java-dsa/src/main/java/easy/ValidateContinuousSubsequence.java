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

        String sequenceStr = "";
        for(Integer n : sequence) {
            sequenceStr += n + ",";
        }
        for(int i = 0;i<array.size() - sequence.size();i++) {
            if(getSubseq(array, i, i+ sequence.size()-1).equals(sequenceStr)) {
                return true;
            }
        }

        return false;
    }

    private static String getSubseq(List<Integer> array, int start, int end) {
        String seq = "";
        for(int i = start;i<end && i<array.size();i++) {
            seq += array.get(i) + ",";
        }
        return seq;
    }
}
