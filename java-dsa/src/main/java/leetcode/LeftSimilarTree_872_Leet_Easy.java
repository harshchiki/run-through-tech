package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/leaf-similar-trees/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 *
 *
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
 * Output: true
 * Example 2:
 *
 *
 * Input: root1 = [1,2,3], root2 = [1,3,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in each tree will be in the range [1, 200].
 * Both of the given trees will have values in the range [0, 200].
 */
public class LeftSimilarTree_872_Leet_Easy {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new LinkedList<>();
        List<Integer> l2 = new LinkedList<>();
        populateList(root1, l1);
        populateList(root2, l2);
        return l1.equals(l2);
    }

    private void populateList(TreeNode node, List<Integer> lst) {
        if(node == null) {
            return;
        }

        if(node.left == null && node.right == null) {
            lst.add(node.val);
        }

        if(node.left != null) {
            populateList(node.left, lst);
        }

        if(node.right != null) {
            populateList(node.right, lst);
        }
    }

    class TreeNode {
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
