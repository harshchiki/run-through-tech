package leetcode;

/**
 * https://leetcode.com/problems/daily-temperatures/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * <p>
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * <p>
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures_739_Leet_Medium {
    public static void main(String[] args) {
        DailyTemperatures_739_Leet_Medium o = new DailyTemperatures_739_Leet_Medium();
        for (int n : o.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})) {
            System.out.print(n + " ");
        }
        System.out.println();

        for (int n : o.dailyTemperatures(new int[]{30, 40, 50, 60})) {
            System.out.print(n + " ");
        }
        System.out.println();

        for (int n : o.dailyTemperatures(new int[]{30, 60, 90})) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] finalArr = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            int j = i + 1;
            for (; j < temperatures.length && temperatures[j] < temperatures[i]; j++) {
            }

            if (j == temperatures.length - 1) {
                if (temperatures[j] > temperatures[i]) {
                    finalArr[i] = j - i;
                } else {
                    finalArr[i] = 0;
                }
            } else if (j == temperatures.length) {
                finalArr[i] = 0;
            } else {
                finalArr[i] = j - i;
            }
        }
        return finalArr;
    }
}
