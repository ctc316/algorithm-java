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
    /**
     * @param hashTable: A list of The first node of linked list
     * @return: A list of The first node of linked list which have twice size
     */
    public ListNode[] rehashing(ListNode[] hashTable) {
        if (hashTable == null || hashTable.length == 0) {
            return hashTable;
        }

        int len = hashTable.length;
        int newLen = len * 2;
        ListNode[] newTable = new ListNode[newLen];

        for (int i = 0; i < len ; i++) {
            ListNode node = hashTable[i];
            while (node != null) {
                int hash = (node.val % newLen + newLen) % newLen;
                ListNode head = newTable[hash];
                ListNode newNode = new ListNode(node.val);
                if (head == null) {
                    newTable[hash] = newNode;
                } else {
                    ListNode tail = getTail(head);
                    tail.next = newNode;
                }
                node = node.next;
            }
        }

        return newTable;
    }

    private ListNode getTail(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (dummy.next != null) {
            dummy = dummy.next;
        }
        return dummy;
    }
};