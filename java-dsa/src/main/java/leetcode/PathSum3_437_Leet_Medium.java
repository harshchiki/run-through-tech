package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/path-sum-iii/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values along the path equals targetSum.
 * <p>
 * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * Output: 3
 * Explanation: The paths that sum to 8 are shown.
 * Example 2:
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 1000].
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class PathSum3_437_Leet_Medium {
    public static void main(String[] args) {
        PathSum3_437_Leet_Medium o = new PathSum3_437_Leet_Medium();

        TreeNode t1 = new TreeNode(10);

        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(-3);

        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(11);

        TreeNode t7 = new TreeNode(3);
        TreeNode t8 = new TreeNode(-2);

        TreeNode t9 = new TreeNode(1);

        t1.left = t2;
        t1.right = t3;

        t2.left = t4;
        t2.right = t5;

        t3.right = t6;

        t4.left = t7;
        t4.right = t8;

        t5.right = t9;

        System.out.println(o.pathSum(t1, 8));
    }


    static int treeNodeCounter = -1;
    List<List<Pair>> lstOfLists;
    Set<Integer> nodesVisited;

    public int pathSum(TreeNode root, int targetSum) {
        treeNodeCounter = 0;
        lstOfLists = new ArrayList<>();
        List<Pair> lst = new ArrayList<>();
        lstOfLists.add(lst);
        prepareLists(root, lst);

        int count = 0;
        nodesVisited = new HashSet<>();
        for (int listIndex = 0; listIndex < lstOfLists.size(); listIndex++) {
            List<Pair> thisList = lstOfLists.get(listIndex);
            boolean hasSumAsTarget = doesHavePathSum(thisList, targetSum, listIndex);
            count += hasSumAsTarget ? 1 : 0;
//            if (hasSumAsTarget) {
//                thisList.forEach(numberPair -> System.out.print("(" + numberPair.left + "," + numberPair.right + ")" + " "));
//                System.out.println();
//            }

        }
        return count;
    }

    private void prepareLists(TreeNode node, List<Pair> currList) {
        if (node == null) {
            return;
        }

        currList.add(new Pair(node.val, ++treeNodeCounter));
        List<Pair> copyCurrList = new LinkedList<>(currList);
        if (node.left != null) {
            prepareLists(node.left, currList);
        }

        if (node.right != null) {
            if (null == node.left) {
                prepareLists(node.right, currList);
            } else {
                List<Pair> newList = new ArrayList<>(copyCurrList);
                lstOfLists.add(newList);
                prepareLists(node.right, newList);
            }
        }
    }

    private boolean doesHavePathSum(List<Pair> lst, int targetSum, int listIndex) {
        int[][] dpSumSubArray = new int[lst.size()][lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            for (int j = i; j < lst.size(); j++) {
                if (i == j) {
                    dpSumSubArray[i][j] = lst.get(i).left;
                } else {
                    dpSumSubArray[i][j] = dpSumSubArray[i][j - 1] + lst.get(j).left;
                }
                if (dpSumSubArray[i][j] == targetSum) {
                    int countConsideredNodes = 0;
                    int n = i;
                    for (; n <= j; n++) {
                        if (nodesVisited.contains(Integer.valueOf(lst.get(n).right))) {
                            countConsideredNodes++;
                        }
                    }

                    for (int p = i; p <= j; p++) {
                        nodesVisited.add(Integer.valueOf(lst.get(p).right));
                    }

                    if (countConsideredNodes != (j-i+1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    class Pair {
        int left, right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
