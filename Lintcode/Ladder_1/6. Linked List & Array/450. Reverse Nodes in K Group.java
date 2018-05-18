/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


public class Solution {
    /*
     * @param head: a ListNode
     * @param k: An integer
     * @return: a ListNode
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        do {
            prev = reverseK(prev, k);
        } while (prev != null);

        return dummy.next;
    }

    // prev -> n1 -> n2 ... nk -> nk+1
    // prev -> nk -> nk-1 .. n1 -> nk+1
    // return n1
    private ListNode reverseK(ListNode prev, int k) {
        // find tail
        ListNode nk = prev;
        for (int i = 0; i < k; i++) {
            if (nk == null) {
                return null;
            }
            nk = nk.next;
        }

        if (nk == null) {
            return null;
        }

        ListNode n1 = prev.next;
        ListNode nkPlus = nk.next;

        // System.out.println(prev.val + "->" + n1.val +"->...->" + nk.val);
        ListNode prevNode = null;
        ListNode node = n1;
        while (node != nkPlus) {
            ListNode temp = node.next;
            node.next = prevNode;
            prevNode = node;
            node = temp;
        }

        // connect to outer list
        prev.next = nk;
        n1.next = nkPlus;

        return n1;
    }
}