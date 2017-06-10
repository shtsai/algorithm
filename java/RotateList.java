/*
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        
        // get len and find tail
        ListNode fast = head;
        int len = 1;
        while (fast.next != null) {
            len++;
            fast = fast.next;
        }
        if (k % len == 0) return head;
        int r = len - (k % len);
        
        ListNode slow = head;
        for (int i = 1; i < r; i++) { slow = slow.next;}
        
        // rotation
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        
        return newHead;
    }
}