package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * You are given an integer array nums and an integer k.
 * <p>
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * <p>
 * Return the maximum number of operations you can perform on the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * Example 2:
 * <p>
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 109
 */
public class MaxNumberOfKSumPairs_1679_Leet_Medium {
    public static void main(String[] args) {
        MaxNumberOfKSumPairs_1679_Leet_Medium obj = new MaxNumberOfKSumPairs_1679_Leet_Medium();
        int[] nums = {1, 2, 3, 4};
        int k = 5;
        System.out.println(obj.maxOperations(nums, k));

        int[] nums2 = {3, 1, 3, 4, 3};
        int k2 = 6;
        System.out.println(obj.maxOperations(nums2, k2));

        int[] nums3 = {4, 4, 4};
        int k3 = 4;
        System.out.println(obj.maxOperations(nums3, k3));
    }

    public int maxOperations(int[] nums, int k) {
        List<Integer> numsLst = Arrays.stream(nums)
            .filter(n -> n < k)
            .boxed()
            .collect(Collectors.toList());
        Collections.sort(numsLst);
        return getMax(numsLst, k);
    }

    int getMax(List<Integer> numsLst, int k) {
        if (numsLst.size() == 0 || numsLst.size() == 1) {
            return 0;
        }
        List<Integer> indices = new ArrayList<>();
        boolean found = false;

        for (int i = 1; i < numsLst.size(); i++) {
            if (numsLst.get(0) + numsLst.get(i) == k) {
                indices.add(0);
                indices.add(i);
                found = true;
                break;
            }
        }

        List<Integer> recList = new ArrayList<>();
        for (int i = found ? 0 : 1; i < numsLst.size(); i++) {
            if (!indices.contains(Integer.valueOf(i))) {
                recList.add(numsLst.get(i));
            }
        }
        return indices.size() / 2 + getMax(recList, k);
    }
}
