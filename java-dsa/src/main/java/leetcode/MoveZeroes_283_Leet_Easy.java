package leetcode;

import java.util.Arrays;

public class MoveZeroes_283_Leet_Easy {
    public static void main(String[] args) {
        int[] a = { 0, 1, 0, 3, 12 };
        Arrays.stream(a).forEach(n -> System.out.print(n + " "));
        new MoveZeroes_283_Leet_Easy().moveZeroes(a);
        System.out.println();
        Arrays.stream(a).forEach(n -> System.out.print(n + " "));
    }
    public void moveZeroes(int[] nums) {
        if (nums.length <= 1) {
            return;
        }

        int left = 0, right = 1;

        while(left < nums.length && right < nums.length && left < right) {
            if(nums[left] == 0 && nums[right] == 0) {
                right++;
            } else if (nums[left] == 0) {
                swap(nums, left, right);
            } else {
                left++;
                right++;
            }
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
