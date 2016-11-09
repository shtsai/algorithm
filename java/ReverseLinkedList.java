/*
 * Reverse a singly linked list.
 */

public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode end = null;     // the end should be null, end of the list
        while (head != null) {
            ListNode next = head.next;   // next stores the next node
            head.next = end;             // let head points to end
            end = head;                  // move end the next node
            head = next;                 // move head to next node
        }
        
        return end;     // return end, because head is null at this point
    }
}