/*
 * Given a singly linked list L: L(0)->L(1)->…->L(n-1)->L(n),
 * reorder it to: L(0)->L(n)->L(1)->L(n-1)->L(2)->L(n-2)->…
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Do the following three steps:
// 1. Find the center of the List
// 2. reverse the second half of the List
// 3. Reorder the list to the interleaving order

// reference: https://discuss.leetcode.com/topic/13869/java-solution-with-3-steps
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode fast = head, slow = head;

        // 1. first find the middle of the list
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;     // slow now points at the middle node
        }

        // 2. reverse the second half
        ListNode reverseHead = slow.next, prev = null;
        while (reverseHead != null) {
            ListNode next = reverseHead.next;
            reverseHead.next = prev;
            prev = reverseHead;
            reverseHead = next;
        }
        slow.next = prev;   // connect the reversed half with the first half

        // 3. mix up the links
        ListNode p1 = head, p2 = slow.next;  // p1 and p2 point to the head of first and second half
        while (p1 != slow) {    // slow is the middle node
            slow.next = p2.next;  // save p2's next
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = slow.next;
        }
        return;
    }
}
