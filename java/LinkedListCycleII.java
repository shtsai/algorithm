/*
	Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

	Note: Do not modify the linked list.

	Follow up:
	Can you solve it without using extra space?
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// Solution 2: Two pointers
// Use fast and slow pointers.
// Fast pointer moves two steps each time, and slow pointer
// moves one step each time. If two pointers meet, then 
// there exists a cycle.
// 
// Time: O(n)
// Space: O(1)
// reference: https://discuss.leetcode.com/topic/5284/concise-o-n-solution-by-using-c-with-detailed-alogrithm-description
// 10/25/2017

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        boolean cycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                cycle = true;
                break;
            }
        }
        
        if (!cycle) return null;
        ListNode p = head;
        while (p != slow) {
            p = p.next;
            slow = slow.next;
        }
        
        return p;
    }
}

// Solution 1: HashSet
// Use a HashSet to keep track of the nodes we have visited.
// When we find a node that we have visited before, return it.
// Otherwise, return null.
// Time: O(n)
// Space: O(n) - hashset
// 10/24/2017

public class Solution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        
        ListNode p = head;
        while (p != null) {
            if (set.contains(p)) {
                return p;
            } else {
                set.add(p);
            }
            p = p.next;
        }
        return null;
    }
}