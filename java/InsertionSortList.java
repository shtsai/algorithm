/*
 * Sort a linked list using insertion sort.
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
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);   // dummy head
        // insert nodes into the sorted portion one by one
        while (head != null) {
            ListNode n = head;
            head = head.next;
            n.next = null;
            insert(dummy, n);
        }
        return dummy.next;
    }
    
    // insert a node into the sorted portion
    public void insert(ListNode dummy, ListNode n) {
        ListNode head = dummy.next; 
        if (head == null) {  // n is first node
            dummy.next = n; 
            return;
        }
        ListNode pre = dummy;
        while (head != null && head.val < n.val) {
            pre = head;
            head = head.next;
        }
        pre.next = n;
        n.next = head;
        return;
    }
}