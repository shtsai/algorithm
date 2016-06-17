/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        Boolean carry = false;
        
        ListNode result = new ListNode(0);
        ListNode current = result;

        while (l1 != null || l2 != null) {
            current.val = l1.val + l2.val;
            if (carry == true) {
                current.val += 1;
                carry = false;
            }
            
            if (current.val >= 10) {
                current.val -= 10;
                carry = true;
            }
            
            if (l1.next == null && l2.next == null && carry == false) {
                break;
            } else if (l1.next == null && l2.next != null) {
                l1.next = new ListNode(0);
            } else if (l1.next != null && l2.next == null) {
                l2.next = new ListNode(0);
            } else if (l1.next == null && l2.next == null && carry == true) {
                l1.next = new ListNode(0);
                l2.next = new ListNode(0);
            }
            
            
            current.next = new ListNode(0);
            current = current.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        return result;
    }
}