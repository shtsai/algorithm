/**
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Solution 2:
// Use a dummy head to hold the result list
// Scan through two lists concurrently, compute value of sum and carry
// When a list reaches the end and the other hasn't, assign value 0 to the null node
// Repeat until both lists reach the their ends.
// Finally, check if carry is zero, if not, append a new node with value carry.
// Time: O(max(m,n))
// Space: O(1)

// version 2:
// 08/28/2018
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int v1, v2;
            if (l1 == null) {
                v1 = 0;
            } else {
                v1 = l1.val;
                l1 = l1.next;
            }
            if (l2 == null) {
                v2 = 0;
            } else {
                v2 = l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode((v1 + v2 + carry) % 10);
            p = p.next;
            carry = (v1 + v2 + carry) / 10;
        }
        return dummy.next;
    }
}

// version 1:
// 10/04/2017
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), p = dummy;
        int carry = 0;
        while (l1 != null || l2!= null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = (num1 + num2 + carry) % 10;
            carry = (num1 + num2 + carry) / 10;
            p.next = new ListNode(sum);
            p = p.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            p.next = new ListNode(carry);
        }
        return dummy.next;
    }
}

// Solution 1:
// Use a flag to indicate carry
// When a list reaches the end, append it with nodes of '0' values
// ??/??/2016
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