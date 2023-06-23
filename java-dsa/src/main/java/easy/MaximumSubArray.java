package easy;

import java.util.HashMap;
import java.util.Map;

public class MaximumSubArray {
    Map<Pair, Integer> sumMap = new HashMap<>();
    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};

        long start = System.nanoTime();
        System.out.println(new MaximumSubArray().maxSubArrayONSquare(nums) + " took " + (System.nanoTime() - start));
        start = System.nanoTime();
        System.out.println(new MaximumSubArray().maxSubArray(nums) + " took " + (System.nanoTime() - start));

    }

    // Time: O(n2), Space: O(n), but faster.
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length;i++) {
            for(int j = 0;j<nums.length;j++) {
                if(this.getSum(nums, i,j) > maxSum) {
                    maxSum = this.getSumOptimized(nums, i, j);
                }
            }
        }
        return maxSum;
    }

    private int getSumOptimized(int[] nums, int start, int end) {
        int sum = 0;
        Pair pair = new Pair(start, end);
        if(start == end) {
            sum = nums[start];
        } else if (end == (start+1)) {
           sum = nums[start] + nums[end];
        } else {
            if(sumMap.containsKey(pair)) {
                sum = sumMap.get(pair).intValue();
            } else {
                sum = getSumOptimized(nums, start, end - 1) + nums[end];
            }
        }

        if(!sumMap.containsKey(pair)) {
            sumMap.put(pair, Integer.valueOf(sum));
        }

        return sum;
    }

    // Time: O(n2), Space: O(1)
    public int maxSubArrayONSquare(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0;i<nums.length;i++) {
            for(int j = 0;j<nums.length;j++) {
                if(this.getSum(nums, i,j) > maxSum) {
                    maxSum = this.getSum(nums, i, j);
                }
            }
        }
        return maxSum;
    }

    private int getSum(int[] nums, int start, int end) {
        int sum = nums[start];
        for(int i = start+1; i<=end; i++) {
            sum+=nums[i];
        }
        return sum;
    }

    private class Pair {
        int start, end;
        Pair(int start, int end) {
            this.start = start; this.end = end;
        }
        public boolean equals(Object o) {
            if (o instanceof Pair) {
                Pair otherPair = (Pair) o;
                return this.start == otherPair.start && this.end == otherPair.end;
            }
            return false;
        }

    }
}
