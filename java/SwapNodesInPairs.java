/*
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode first = head;
        ListNode second = head.next;
        ListNode prev = null;
        head = head.next;
        
        while (first != null && second != null) {
            // swap
            if (prev != null) prev.next = second;
            first.next = second.next;
            second.next = first;
            
            // move to next pair
            prev = first;
            first = first.next;
            if (first == null || first.next == null) {
                second = null;
            } else {
                second = first.next;
            }
        }
        
        return head;
    }
}