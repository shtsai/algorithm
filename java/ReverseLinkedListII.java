/*
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 *
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 *
 * return 1->4->3->2->5->NULL.
 *
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ≤ m ≤ n ≤ length of list.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Solution 3: 
// Four pointers:
//      - dummy: head of the list
//      - pre: node at index m - 1, right before reverse
//      - start: node at index m, first node in the reverse part
//      - next: a moving pointer that moves toward index n
//
// Time: O(n) - one pass
// Space: O(1)
// reference: https://discuss.leetcode.com/topic/8976/simple-java-solution-with-clear-explanation
// 01/09/2018
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1), pre = dummy;
        dummy.next = head;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        ListNode start = pre.next, next = start.next;
        
        for (int i = 0; i < n - m; i++) {
            start.next = next.next;
            next.next = pre.next;
            pre.next = next;
            next = start.next;
        }
        
        return dummy.next;
    }
}

// Solution 2:
// Use a dummy head to hold the linked list.
// Find the start and end point of the reverse part, and do the reverse.
// Then connect the reversed portion with the original list.
// Time: O(n) - one pass
// Space: O(1)
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = head, prev = dummy, start = null, prevStart = dummy;
        int index = 1;
        while (p != null) {
            if (index > m && index < n) {  // start reverse
                ListNode next = p.next;
                p.next = prev;
                prev = p;
                p = next;
            } else if (index == n) {
                start.next = p.next;    // link the start of reverse part to the remaining list
                p.next = prev;
                prevStart.next = p;
                break;
            }  else {
                if (index == m -1) prevStart = p;
                if (index == m) start = p;
                prev = p;
                p = p.next;
            }
            index++;
        }
        return dummy.next;
    }
}


// Solution 1:
// First break the list at m and n.
// Then reverse from m to n.
// Finally connect them back with original list.
// Time: O(n)
// Space: O(1)
// 01/09/2018
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1), p = dummy, prev = null;
        dummy.next = head;
        int i = 0;
        while (i < m) {
            prev = p;
            p = p.next;
            i++;
        }
        ListNode rStart = p;       // find reverse start
        while (i < n) {
            p = p.next;
            i++;
        }
        ListNode rEnd = p, remaining = p.next;  // find reverse end
        prev.next = null;       // break the list at m and n
        rEnd.next = null;
        reverse(rStart);
        prev.next = rEnd;       // reconnect them
        rStart.next = remaining;
        return dummy.next;
    }
    
    public void reverse(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
    }
}