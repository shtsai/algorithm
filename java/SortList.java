/*
 * Sort a linked list in O(n log n) time using constant space complexity.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
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
    // merge sort
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len++;
            p = p.next;
        }
        
        p = head;
        ListNode next = p.next;
        for (int i = 0; i < len/2-1; i++) {
            p = next;
            next = next.next;
        }
        p.next = null; // break the link between two sublists
        
        ListNode first = sortList(head);
        ListNode second = sortList(next);
        return merge(first, second);
    }
    // merge two sorted lists    
    public ListNode merge(ListNode a, ListNode b) {
        ListNode head = new ListNode(0);   // dummy head for the merged list
        ListNode p = head;
        while (a != null && b != null) {
            if (a.val < b.val) {
                p.next = a;
                a = a.next;
                p = p.next;
            } else {
                p.next = b;
                b = b.next;
                p = p.next;
            }
        }
        
        if (a == null) p.next = b;
        else if (b == null) p.next = a;
        
        return head.next;
    }
}
