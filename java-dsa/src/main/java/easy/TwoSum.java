package easy;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{15,11,7,2};
        int target = 9;
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
                result[0] = i;
                result[1] = map.get(Integer.valueOf(lookupNum));
                return result;
            }
        }
        return result;
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
