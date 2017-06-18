/*
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 * 
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// solution 2: using stack 
// O(n) time, O(n) space
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
        Stack<ListNode> num1 = new Stack<>();
        Stack<ListNode> num2 = new Stack<>();
        Stack<ListNode> sum = new Stack<>();
        
        // push all nodes into the stack
        ListNode p = l1;
        while (p != null) {
            num1.push(p);
            p = p.next;
        }
        
        p = l2;
        while (p != null) {
            num2.push(p);
            p = p.next;
        }
        
        ListNode result = new ListNode(0);
        int carry = 0;
        p = result;
        // perform calculation node by node
        while ((!num1.empty()) && (!num2.empty())) {
            int x = num1.pop().val;
            int y = num2.pop().val;
            sum.push(new ListNode((x + y + carry) % 10));
            carry = (x + y + carry) / 10;
        }
        
        // handle different list length
        if (num1.empty()) {
            while (!num2.empty()) {
                int x = num2.pop().val;
                sum.push(new ListNode((x + carry) % 10));
                carry = (x + carry) / 10;
            }
        } else if (num2.empty()) {
            while (!num1.empty()) {
                int x = num1.pop().val;
                sum.push(new ListNode((x + carry) % 10));
                carry = (x + carry) / 10;
            }
        }
        
        // don't forget to add the carry to the result
        if (carry != 0) sum.push(new ListNode(carry));
        
        // convert the sum stack back into a list
        while (!sum.empty()) {
            p.next = new ListNode(sum.pop().val);
            p = p.next;
        }
        
        return result.next;
    }
}


// solution 1: convert list into int, then calculate
// problem: int overflow, did not pass all test cases
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int num1 = 0, num2 = 0;
        ListNode p = l1;
        while (p != null) {
            num1 = num1 * 10 + p.val;
            p = p.next;
        }
        
        p = l2;
        while (p != null) {
            num2 = num2 * 10 + p.val;
            p = p.next;
        }
        
        System.out.println(num1);
        System.out.println(num2);
        int sum = num1 + num2;
        
        // corner case
        if (sum == 0) return new ListNode(0);
        
        p = null;
        while (sum != 0) {
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = p;
            sum /= 10;
            p = newNode;
        }
        
        return p;
        
    }
}