/*
 * Given a linked list, remove the nth node from the end of list and return its head.
 *
 * For example,
 *
 *   Given linked list: 1->2->3->4->5, and n = 2.
 *
 *   After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Solution 2: one pass
// use fast and slow pointers
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head, pre = null;
        for (int i = 0; i < n; i++) {   // move fast forward n nodes
            fast = fast.next;
        }
        while (fast != null) {  // when fast reaches the end, slow is at the delete position
            fast = fast.next;
            pre = slow;
            slow = slow.next;
        }
        
        if (pre == null) return slow.next;
        pre.next = slow.next;
        return head;
    }
}

// Solution 1: two passes
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        
        p = head;
        ListNode pre = null;
        for (int i = 0; i < len - n; i++) {
            pre = p;
            p = p.next;
        }
        if (pre != null) { 
            pre.next = p.next; 
            return head;
        } else {    // if pre == null, the first node should be removed
            return p.next;
        }
    }
}