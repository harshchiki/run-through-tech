package leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * https://leetcode.com/problems/product-of-array-except-self/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * <p>
 * <p>
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductOfArrayExceptSelf_238_Leet_Medium {

    public static void main(String... args) {
        int[] a = {1,2,3,4};
        System.out.println("a original");
        Arrays.stream(a).forEach(n -> System.out.print(n + " "));
        System.out.println();
        Arrays.stream(new ProductOfArrayExceptSelf_238_Leet_Medium().productExceptSelf(a)).forEach(n -> System.out.print(n + " "));
        System.out.println();
        int[] b = {-1,1,0,-3,3};
        System.out.println("b original");
        Arrays.stream(b).forEach(n -> System.out.print(n + " "));
        System.out.println();
        Arrays.stream(new ProductOfArrayExceptSelf_238_Leet_Medium().productExceptSelf(b)).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    public static int[] readArrayFromResource(String resourceName) {
        try (InputStream inputStream = ProductOfArrayExceptSelf_238_Leet_Medium.class.getResourceAsStream(resourceName)) {
            if (inputStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                List<Integer> list = new ArrayList<>();

                String line;
                while ((line = br.readLine()) != null) {
                    list.add(Integer.parseInt(line));
                }

                int[] array = new int[list.size()];
                for (int i = 0; i < array.length; i++) {
                    array[i] = list.get(i);
                }

                return array;
            } else {
                System.err.println("Resource not found: " + resourceName);
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int[] productExceptSelf(int[] nums) {
        int[] finalArr = new int[nums.length];

        Map<Pair, Integer> prodMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (j == i) {
                    prodMap.put(new Pair(i, j), Integer.valueOf(nums[i]));
                } else {
                    prodMap.put(new Pair(i, j), getProduct(nums, i, j, prodMap));
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if(i == 0) {
                finalArr[i] = prodMap.get(new Pair(1, nums.length - 1));
            } else if (i == nums.length - 1) {
                finalArr[i] = prodMap.get(new Pair(0, nums.length-2));
            } else {
                finalArr[i] = prodMap.get(new Pair(0,i-1)) * prodMap.get(new Pair(i+1, nums.length-1));
            }
        }

        return finalArr;
    }

    private Integer getProduct(int[] arr, int start, int end, Map<Pair, Integer> prodMap) {
        int product = arr[start];
        Pair pair = new Pair(start, end - 1);
         if (prodMap.containsKey(pair)) {
            product = prodMap.get(pair).intValue() * arr[end];
        } else {
            for (int i = start + 1; i < end; i++) {
                product *= arr[i];
            }
        }

        return Integer.valueOf(product);
    }

    class Pair {
        int start, end;

        Pair(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return start == pair.start && end == pair.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
}
