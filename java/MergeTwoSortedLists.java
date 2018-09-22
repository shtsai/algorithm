/**
    Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

    Example:
    Input: 1->2->4, 1->3->4
    Output: 1->1->2->3->4->4
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Solution 2: Iterative solution
// Time: O(m+n)
// Space: O(1)
// 09/21/2018
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int v2 = l2 == null ? Integer.MAX_VALUE : l2.val;
            if (v1 < v2) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        return dummy.next;
    }
}

// Solution 1: recursive 
// Time: O(m + n)
// Space: O(m + n) - call stack
// 09/21/2018
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode result;
        if (l1.val < l2.val) {
            result = l1;
            result.next = mergeTwoLists(l1.next, l2);
        } else {
            result = l2;
            result.next = mergeTwoLists(l1, l2.next);
        }
        return result;
    }
}