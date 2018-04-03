/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) {
            return null;
        }

        /* Version 1: HashMap, O(n) Space */
        RandomListNode dummy = new RandomListNode(0);
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        RandomListNode src = head;
        RandomListNode dest = dummy;
        while (src != null) {
            RandomListNode newNode;
            if (map.containsKey(src)) {
                newNode = map.get(src);
            } else {
                newNode = new RandomListNode(src.label);
                map.put(src, newNode);
            }

            if (src.random != null) {
                if (map.containsKey(src.random)) {
                    newNode.random = map.get(src.random);
                } else {
                    newNode.random = new RandomListNode(src.random.label);
                    map.put(src.random, newNode.random);
                }
            }

            dest.next = newNode;
            dest = dest.next;
            src = src.next;
        }

        return dummy.next;


        /* Version 2: Next Link, O(1) Space */
        /* 1->1`->2->2`->3->3`->4->4`, (1) copy nodes, (2) copy edges (random), (3) split */

        // Copy Nodes
        RandomListNode curr = head;
        while (curr != null) {
            RandomListNode newNode = new RandomListNode(curr.label);
            newNode.next = curr.next;
            newNode.random = curr.random;
            curr.next = newNode;
            curr = newNode.next;
        }

        // Copy Randoms
        curr = head;
        while (curr != null) {
            if (curr.next.random != null) {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Split List
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode target = dummy;
        curr = head;
        while (curr != null) {
            target.next = curr.next;
            curr = curr.next.next;
            target = target.next;
        }

        return dummy.next;
    }
}