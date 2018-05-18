/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */

//Version 1: Merge Sort
public class Solution {
    /*
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMid(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);

        return mergeSort(left, right);
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode mergeSort(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }

        if (head1 != null) {
            curr.next = head1;
        } else {
            curr.next = head2;
        }

        return dummy.next;
    }
}



//Version 2: Quick Sort
public class Solution {
    /*
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
        ListNode midDummy = new ListNode(0), midTail = midDummy;
        ListNode rightDummy = new ListNode(0), rightTail = rightDummy;

        // Quick sort
        ListNode mid = findMid(head);
        while (head != null) {
            if (head.val < mid.val) {
                leftTail.next = head;
                leftTail = leftTail.next;
            } else if (head.val > mid.val) {
                rightTail.next = head;
                rightTail = rightTail.next;
            } else {
                midTail.next = head;
                midTail = midTail.next;
            }
            head = head.next;
        }

        // Cut
        leftTail.next = null;
        midTail.next = null;
        rightTail.next =null;

        // Concat
        return concat(sortList(leftDummy.next), midDummy.next, sortList(rightDummy.next));
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode concat(ListNode left, ListNode mid, ListNode right) {
        ListNode dummy = new ListNode(0), tail = dummy;
        if (left != null) {
            tail.next = left;
            tail = findTail(left);
        }
        if (mid != null) {
            tail.next = mid;
            tail = findTail(mid);
        }
        if (right != null) {
            tail.next = right;
            tail = findTail(right);
        }
        return dummy.next;
    }

    private ListNode findTail(ListNode head) {
        if (head == null) {
            return null;
        }
        while (head.next != null) {
            head = head.next;
        }
        return head;
    }
}