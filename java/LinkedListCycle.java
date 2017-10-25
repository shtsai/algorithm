/*
    Given a linked list, determine if it has a cycle in it.

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

// Solution 2:
// slow and fast pointer
// Scan the list with two pointers.
// If two pointers meet, there must exists a cycle.
// Time: O(j + k) = O(n) - j is the length of the non-cyclic part
//                         k is the length of cycle
// Space: O(1)
// 10/24/2017

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

// Solution 1: HashSet
// Use a hashSet to keep track of the nodes that we have visited
// Time: O(n) - n is the length of the list
// Space: O(n)
// 10/24/2017

public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        
        ListNode p = head;
        while (p != null) {
            if (set.contains(p)) return true;
            else set.add(p);
            p = p.next;
        }
        
        return false;
    }
}