package leetcode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * Example 3:
 *
 * Input: root = [], key = 0
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 104].
 * -105 <= Node.val <= 105
 * Each node has a unique value.
 * root is a valid binary search tree.
 * -105 <= key <= 105
 */
public class DeleteNodeInBST_450_Leet_Medium {

    public static void main(String[] args) {
        DeleteNodeInBST_450_Leet_Medium o = new DeleteNodeInBST_450_Leet_Medium();

        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t5.left = t3;
        t5.right = t6;
        t3.left = t2;
        t3.right = t4;
        t6.right = t7;
        o.deleteNode(t5, 7);

    }

    public TreeNode deleteNode(TreeNode root, int key)  {
        if(root == null) {
            return root;
        }
        if(root.val == key) {
            // root is to be deleted
            Stack<TreeNode> stack = new Stack<>();
            if(root.right != null) {
                TreeNode node = root.right;
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                }
            } else if (root.left != null) {
                TreeNode node = root.left;
                while(node != null) {
                    stack.push(node);
                    node = node.right;
                }
            } else {
                return null; // empty tree
            }
            if(stack.size() >= 2) {
                TreeNode node = stack.pop();
                node.left = root.left;
                node.right = root.right;
                root = node;
                return root;
            } else {
                // 1
                root.right.left = root.left;
                root = root.right;
                return root;
            }
        } else {
            // non root is to be deleted
            TreeNode parent = getNodesParent(root, key > root.val ? root.right : root.left, key);
            if(parent == null) {
                return root; // not found
            }
            TreeNode nodeToBeDeleted = parent.right.val == key ? parent.right : parent.left;

            Stack<TreeNode> stack = new Stack<>();
            if(nodeToBeDeleted.right != null) {
                TreeNode node = nodeToBeDeleted.right;
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                }
            } else {
                TreeNode node = nodeToBeDeleted.left;
                while(node != null) {
                    stack.push(node);
                    node = node.right;
                }
            }
            if(stack.size() >= 2) {
                TreeNode replacementNode = stack.pop();
                if(nodeToBeDeleted.right != null) {
                    stack.pop().left = null;
                } else {
                    stack.pop().right = null;
                }
                if(parent.left.val == key) {
                    // parents' left child to be deleted
                    parent.left = replacementNode;
                } else {
                    // parent's right child to be deleted
                    parent.right = replacementNode;
                }
                replacementNode.left = nodeToBeDeleted.left;
                replacementNode.right = nodeToBeDeleted.right;

            } else if (stack.size() == 1) {
                TreeNode node = stack.pop();
                if(parent.left.val == key) {
                    // parents' left child to be deleted
                    parent.left = node;
                    node.left = nodeToBeDeleted.left;
                } else {
                    // parent's right child to be deleted
                    parent.right = node;
                    node.right = nodeToBeDeleted.right;
                }
            } else {
                // 0
                parent.left = null;
                parent.right = null;
            }
        }
        return root;
    }

    private TreeNode getNodesParent(TreeNode parent, TreeNode node, int key) {
        if(node == null) {
            return null;
        }
        if(node.val == key) {
            return parent;
        } else if (node.val < key) {
            return getNodesParent(node, node.right, key);
        } else {
            return getNodesParent(node, node.left, key);
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
