/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */


public class Solution {
    /*
     * @param node: a list node in the list
     * @param x: An integer
     * @return: the inserted new list node
     */
    public ListNode insert(ListNode node, int x) {
        ListNode retNode = new ListNode(x);

        if (node == null) {
            retNode.next = retNode;
            return retNode;
        }

        ListNode prev = node;
        ListNode curr = node.next;
        while (curr != node) {
            if (prev.val <= x && x <= curr.val ||
                prev.val > curr.val && (x >= prev.val || x <= curr.val)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        prev.next = retNode;
        retNode.next = curr;

        return retNode;
    }
}