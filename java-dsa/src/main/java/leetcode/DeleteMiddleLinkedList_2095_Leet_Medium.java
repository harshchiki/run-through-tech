package leetcode;

/**
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/?envType=study-plan-v2&envId=leetcode-75
 * <p>
 * You are given the head of a linked list. Delete the middle node, and return the head of the modified linked list.
 * <p>
 * The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.
 * <p>
 * For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,3,4,7,1,2,6]
 * Output: [1,3,4,1,2,6]
 * Explanation:
 * The above figure represents the given linked list. The indices of the nodes are written below.
 * Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
 * We return the new list after removing this node.
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [1,2,4]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 4, node 2 with value 3 is the middle node, which is marked in red.
 * Example 3:
 * <p>
 * <p>
 * Input: head = [2,1]
 * Output: [2]
 * Explanation:
 * The above figure represents the given linked list.
 * For n = 2, node 1 with value 1 is the middle node, which is marked in red.
 * Node 0 with value 2 is the only node remaining after removing node 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 105].
 * 1 <= Node.val <= 105
 */
public class DeleteMiddleLinkedList_2095_Leet_Medium {

    public static void main(String[] args) {
        DeleteMiddleLinkedList_2095_Leet_Medium o = new DeleteMiddleLinkedList_2095_Leet_Medium();
        int[] a = {1, 3, 4, 7, 1, 2, 6};
        ListNode aHead = o.initList(a);
        o.printList(aHead);
        o.deleteMiddle(aHead);
        System.out.println();
        System.out.println("deleted middle");
        System.out.println();
        o.printList(aHead);


//        int[] b = {1,2,3,4};
//        ListNode bHead = o.initList(b);
//        o.printList(bHead);
//        o.deleteMiddle(bHead);
//        System.out.println();
//        System.out.println("deleted middle");
//        System.out.println();
//        o.printList(bHead);
    }

    public ListNode deleteMiddle(ListNode head) {
        if(head == null) {
            return head;
        }

        if(head.next == null) {
            head = null;
            return head;
        }

        if(head.next.next == null) {
            head.next = null;
            return head;
        }

        ListNode prev = head;
        ListNode next = head.next;
        ListNode jumper = head.next.next;

        while (jumper != null && jumper.next != null) {
            prev = prev.next;
            next = next.next;
            jumper = jumper.next.next;
        }

        prev.next = next.next;
        return head;
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
