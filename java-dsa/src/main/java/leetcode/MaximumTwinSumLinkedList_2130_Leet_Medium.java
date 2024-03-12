package leetcode;

/**
 * https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 * <p>
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 * <p>
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [5,4,2,1]
 * Output: 6
 * Explanation:
 * Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
 * There are no other nodes with twins in the linked list.
 * Thus, the maximum twin sum of the linked list is 6.
 * Example 2:
 * <p>
 * <p>
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation:
 * The nodes with twins present in this linked list are:
 * - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
 * - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
 * Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
 * Example 3:
 * <p>
 * <p>
 * Input: head = [1,100000]
 * Output: 100001
 * Explanation:
 * There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is an even integer in the range [2, 105].
 * 1 <= Node.val <= 105
 */
public class MaximumTwinSumLinkedList_2130_Leet_Medium {
    public static void main(String[] args) {
        MaximumTwinSumLinkedList_2130_Leet_Medium o = new MaximumTwinSumLinkedList_2130_Leet_Medium();
        int[] a = {5, 4, 2, 1};
        ListNode aHead = o.initList(a);
        System.out.println("a : " + o.pairSum(aHead));

        int[] b = {4, 2, 2, 3};
        ListNode bHead = o.initList(b);
        System.out.println("b : " + o.pairSum(bHead));

        int[] c = {1, 100000};
        ListNode cHead = o.initList(c);
        System.out.println("c : " + o.pairSum(cHead));
    }

    public int pairSum(ListNode head) {
        int maxSum = Integer.MIN_VALUE;
        int n = 0;
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }

        ListNode leftNode = head;
        ListNode rightNode = head;
        for (int i = 0; i < n / 2; i++) {
            rightNode = head;
            for (int j = 0; j < n - i - 1; j++) {
                rightNode = rightNode.next;
            }
            if (leftNode.val + rightNode.val > maxSum) {
                maxSum = leftNode.val + rightNode.val;
            }
            leftNode = leftNode.next;
        }
        return maxSum;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private void printList(ListNode p) {
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }

    private ListNode initList(int[] a) {
        ListNode head = new ListNode(a[0]);

        ListNode p = head;
        for (int i = 1; i < a.length; i++) {
            p.next = new ListNode(a[i]);
            p = p.next;
        }

        return head;
    }
}
