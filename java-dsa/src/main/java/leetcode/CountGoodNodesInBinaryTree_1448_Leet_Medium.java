package leetcode;

/**
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * Example 2:
 *
 *
 *
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 *
 *
 * Constraints:
 *
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 */
public class CountGoodNodesInBinaryTree_1448_Leet_Medium {
    public static void main(String[] args) {
        CountGoodNodesInBinaryTree_1448_Leet_Medium o = new CountGoodNodesInBinaryTree_1448_Leet_Medium();
        countOfGoodNodes = 0;
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(4);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(1);
        TreeNode t6 = new TreeNode(5);

        t1.left = t2;
        t1.right = t3;

        t2.left = t4;

        t3.left = t5;
        t3.right = t6;

        System.out.println(o.goodNodes(t1));

        countOfGoodNodes = 0;
        TreeNode t11 = new TreeNode(3);
        TreeNode t12 = new TreeNode(3);
        TreeNode t13 = new TreeNode(4);
        TreeNode t14 = new TreeNode(2);
        t11.left = t12;
        t12.left = t13;
        t13.right = t14;
        System.out.println(o.goodNodes(t11));

        countOfGoodNodes = 0;
        TreeNode t111 = new TreeNode(1);
        System.out.println(o.goodNodes(t111));
    }

    static int countOfGoodNodes = 0;
    public int goodNodes(TreeNode root) {
        countOfGoodNodes = 0;
        if(root == null) {
            return countOfGoodNodes;
        } else {
            countOfGoodNodes++;
        }

        traverse(root.left, root.val);
        traverse(root.right, root.val);
        return countOfGoodNodes;
    }

    public void traverse(TreeNode node, int max) {
        if(node == null) {
            return;
        }

        if(node.val >= max) {
            countOfGoodNodes++;
            max = node.val;
        }

        traverse(node.left, max);
        traverse(node.right, max);
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
