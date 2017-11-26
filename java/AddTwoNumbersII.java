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

// Solution 2 version 3: much more concise
// 11/14/2017
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode p = l1;
        while (p != null) {
            s1.push(p.val);
            p = p.next;
        }
        p = l2;
        while (p != null) {
            s2.push(p.val);
            p = p.next;
        }
        int carry = 0;
        ListNode dummy = new ListNode(-1), node = null;
        while (!(s1.isEmpty() && s2.isEmpty() && carry == 0)) {
            int n1 = s1.isEmpty() ? 0 : s1.pop();
            int n2 = s2.isEmpty() ? 0 : s2.pop();
            ListNode newNode = new ListNode((n1 + n2 + carry) % 10);
            newNode.next = node;    // insert after dummy
            dummy.next = newNode;
            node = newNode;
            carry = (n1 + n2 + carry) / 10;
        }
        return dummy.next;
    }
}

// Solution 2 version 2:
// Use a dummy node to hold the result list
// add nodes to the result list while computing.
// Save one pass
// Time: O(n), Space: O(n)
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode p = l1;
        while (p != null) {
            s1.push(p.val);
            p = p.next;
        }
        p = l2;
        while (p != null) {
            s2.push(p.val);
            p = p.next;
        }
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int n1 = s1.pop();
            int n2 = s2.pop();
            ListNode newNode = new ListNode((n1+n2+carry)%10);
            carry = (n1+n2+carry) / 10;
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        while (!s1.isEmpty()) {
            int n1 = s1.pop();
            ListNode newNode = new ListNode((n1+carry)%10);
            carry = (n1+carry) / 10;
            newNode.next = dummy.next;
            dummy.next = newNode;
        } 
        while (!s2.isEmpty()) {
            int n2 = s2.pop();
            ListNode newNode = new ListNode((n2+carry)%10);
            carry = (n2+carry) / 10;
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        if (carry != 0) {
            ListNode newNode = new ListNode(carry);
            newNode.next = dummy.next;
            dummy.next = newNode;
        }
        return dummy.next;
    }
}


// solution 2: using stack 
// Push two lists onto two stacks, so we can get LIFO order
// Compute sum digit by digit, push result onto sum stack.
// Build result list from the sum stack.
// O(n) time, O(n) space

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