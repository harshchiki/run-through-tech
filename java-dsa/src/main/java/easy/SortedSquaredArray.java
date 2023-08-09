package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedSquaredArray {
    public int[] sortedSquaredArray(int[] array) {
        List<Integer> result = new ArrayList<>();
        for(int i =0 ; i < array.length; i++) {
            result.add(array[i] * array[i]);
        }
        Collections.sort(result);
        int[] resultArr = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }
}
