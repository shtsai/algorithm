/*
 * Reverse a singly linked list.
 */

// Solution 3: in-place
// Time: O(n)
// Space: O(1)
// 01/09/2018
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode p = head, prev = null;
        while (p != null) {
            ListNode next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }
        return prev;
    }
}

// Solution 2: Recursion
// Recursively reverse the remaining list, then append current node at the end.
// This can be done by doing (head.next.next = head)
// Draw it on a piece of paper to understand it
// Time: O(n)
// Space: O(n) - stack
// 01/09/2018
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode reversed = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reversed;
    }
}

// Solution 1: Stack
// Push all nodes to a stack, then pop them from stack to reverse them
// Time: O(n) - two-pass
// Space: O(n) - stack
// 01/09/2018
class Solution {
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        ListNode dummy = new ListNode(-1), p = dummy;
        while (!stack.isEmpty()) {
            p.next = stack.pop();
            p = p.next;
        }
        p.next = null;
        return dummy.next;
    }
}

