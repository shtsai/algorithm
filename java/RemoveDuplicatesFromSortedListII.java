/*
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 *
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
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
// more consice
// reference: https://discuss.leetcode.com/topic/3890/my-accepted-java-code/2
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0), p = head, prev = dummy;
        dummy.next = head;
        while (p != null) {
            while (p.next != null && p.val == p.next.val) { // traverse to the end of duplcates
                p = p.next;
            }
            if (prev.next == p) {  // no duplicates, p is right after prev
                prev = p;
            } else {    // remove all the duplicates
                prev.next = p.next;
            }
            p = p.next;

        }
        return dummy.next;
    }
}

// Solution 1:
// use a dummy head to hold the list
// maintain the prev non-duplcate node and the count of the nodes
// if find count > 1, remove all nodes in-between
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0), p = head, prev = dummy;
        dummy.next = head;
        int value = Integer.MAX_VALUE, count = -1;
        while (p != null) {
            if (p.val == value) {   // p has the same value as the previous node
                count++;
            } else {    // find a different number
                // first check if prev nodes are duplicates
                if (count > 1) {
                    prev.next = p;       // delete all duplicate nodes between pre and p
                }
                if (p.next != null && p.next.val != p.val) prev = p;
                value = p.val;
                count = 1;
            }
            p = p.next;

        }
        if (count > 1) {
            prev.next = null;
        }
        return dummy.next;
    }
}
