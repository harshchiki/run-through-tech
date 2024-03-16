package leetcode;

/**
 * https://leetcode.com/problems/maximum-average-subarray-i/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * You are given an integer array nums consisting of n elements, and an integer k.
 *
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. Any answer with a calculation error less than 10-5 will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * Example 2:
 *
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 */
public class MaxAvgSubArray_643_Leet_Easy {
    public static void main(String[] args) {
        MaxAvgSubArray_643_Leet_Easy o = new MaxAvgSubArray_643_Leet_Easy();
        System.out.println(o.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println(o.findMaxAverage(new int[]{5}, 1));
    }

    // this is suboptimal
    // more optimal: based on (a+b+c)/k = a/k + b/k + c/k. we cam get (0,k) - O(n), then looping from k+1 to n, subtracting k from this index divide by k and adding new element/k - and getting avg
    public double findMaxAverage(int[] nums, int k) {
        double maxAvg = Double.MIN_VALUE;
        for(int i = 0; i < nums.length - k;i++) {
            double sum = 0.0;
            for (int j = i; j < i+k; j++) {
                sum += nums[j];
            }
            maxAvg = sum/k > maxAvg ? sum/k : maxAvg;
        }
        return maxAvg;
    }
}
