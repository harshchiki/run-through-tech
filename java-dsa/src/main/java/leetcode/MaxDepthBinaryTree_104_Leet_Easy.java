package leetcode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Example 2:
 *
 * Input: root = [1,null,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -100 <= Node.val <= 100
 */
public class MaxDepthBinaryTree_104_Leet_Easy {
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return getDepth(root, 1);
    }

    private int getDepth(TreeNode node, int depth) {
        if(node.left == null && node.right == null) {
            return depth;
        }
        int leftDepth = depth, rightDepth = depth;
        if(node.left != null) {
            leftDepth = getDepth(node.left, depth+1);
        }

        if(node.right != null) {
            rightDepth = getDepth(node.right, depth+1);
        }

        return leftDepth > rightDepth ? leftDepth : rightDepth;
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
