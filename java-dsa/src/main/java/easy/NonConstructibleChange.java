package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of coins ( array holding their values)
 * What is the minimum amount of change that you cannot create out of all those
 */
public class NonConstructibleChange {

    public static void main(String[] args) {
        System.out.println(new NonConstructibleChange().nonConstructibleChange(new int[] {5, 7, 1, 1, 2, 3, 22 }));
    }
    public int nonConstructibleChange(int[] coins) {
        List<Integer> lstCoins = new ArrayList<>();
        int totalSum = 0;
        int smallestValue = -1;
        for(int coin : coins) {
            lstCoins.add(Integer.valueOf(coin));
            totalSum += coin;
            smallestValue = coin < smallestValue ? coin : smallestValue;
        }
        Collections.sort(lstCoins);
        int minPossibleSum = -1;
        for(int i = smallestValue; i < totalSum; i++) {
            int tmpSum = 0;
            for(int j = 0;j< lstCoins.size();j++) {
                
            }
        }

        // coins is sorted
        return -1;
    }
}

