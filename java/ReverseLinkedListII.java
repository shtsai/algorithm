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

// Solution 1:
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
