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

// Solution 2: Stack + dummy node
// Push two lists onto two stacks, so we can get LIFO order
// Compute sum digit by digit
// Use a dummy node to hold the result list
// add nodes to the result list while computing.
// Time: O(n)
// Space: O(n)
// 09/18/2018
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        
        ListNode dummy = new ListNode(-1);
        int carry = 0;
        while (!(stack1.isEmpty() && stack2.isEmpty() && carry == 0)) {
            int i1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int i2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            ListNode tail = dummy.next;
            dummy.next = new ListNode((i1 + i2 + carry) % 10);
            dummy.next.next = tail;
            carry = (i1 + i2 + carry) / 10;
        }

        return dummy.next;
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