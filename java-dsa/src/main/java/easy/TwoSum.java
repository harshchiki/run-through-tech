package easy;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/two-sum
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{14};
        int target = 15;
        int[] result = new TwoSum().twoSum(nums, target);
        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
    // Time: O(n), Space: O(n)
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i< nums.length; i++) {
            map.put(Integer.valueOf(nums[i]), Integer.valueOf(i));
        }
        for(int i = 0; i < nums.length;i++) {
            int lookupNum = target - nums[i];
            if(map.containsKey(Integer.valueOf(lookupNum)) && (map.get(Integer.valueOf(lookupNum).intValue())) != i) {
                result[0] = nums[i];
                result[1] = nums[map.get(Integer.valueOf(lookupNum))];
                return result;
            }
        }
        return new int[0];
    }

    // Time: O(n2), Space: O(1)
    public int[] twoSumONSquare(int[] nums, int target) {
        int[] result = new int[2];
        for(int i = 0;i < nums.length-1; i++) {
            for (int j = i+1; i< nums.length; j++) {
                if(target == (i + j)) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
