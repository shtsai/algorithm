/*
 * Given a singly linked list L: L(0)->L(1)->…->L(n-1)->L(n),
 * reorder it to: L(0)->L(n)->L(1)->L(n-1)->L(2)->L(n-2)->…
 *
 * You must do this in-place without altering the nodes' values.
 *
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
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
// Do the following three steps:
// 1. Find the center of the List
// 2. reverse the second half of the List
// 3. Reorder the list to the interleaving order
// reference: https://discuss.leetcode.com/topic/13869/java-solution-with-3-steps
//
// Time: O(n)
// Space: O(1)

// version 2:
// 08/30/2018
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode tailHead = slow.next;
        slow.next = null;               // break two parts
        tailHead = reverse(tailHead);   // reverse second half
        
        ListNode p = head, q = tailHead;
        while (p != null && q != null) {
            ListNode pNext = p.next;
            ListNode qNext = q.next;
            p.next = q;
            q.next = pNext;
            p = pNext;
            q = qNext;
        }
    }
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    
}

// version 1:
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode fast = head, slow = head;

        // 1. first find the middle of the list
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;     // slow now points at the middle node
        }

        // 2. reverse the second half
        ListNode reverseHead = slow.next, prev = null;
        while (reverseHead != null) {
            ListNode next = reverseHead.next;
            reverseHead.next = prev;
            prev = reverseHead;
            reverseHead = next;
        }
        slow.next = prev;   // connect the reversed half with the first half

        // 3. mix up the links
        ListNode p1 = head, p2 = slow.next;  // p1 and p2 point to the head of first and second half
        while (p1 != slow) {    // slow is the middle node
            slow.next = p2.next;  // save p2's next
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = slow.next;
        }
        return;
    }
}

// Solution 1: Two pointers
// Store all nodes in an array
// Use two pointer to mix up both end
// 
// Time: O(n)
// Space: O(n)
// 08/30/2018

class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        int len = 0;
        while (head != null) {
            list.add(head);
            len++;
            ListNode next = head.next;
            head.next = null;       // break links
            head = next;
        }
        ListNode prev = null;
        int end = len % 2 == 0 ? len / 2 : len / 2 + 1;   // end length differ for odd and even
        for (int i = 0; i < end; i++) {

            ListNode left = list.get(i);
            ListNode right = list.get(len - 1 - i);
            if (prev != null) {
                prev.next = left;
            }
            if (left != right) {    // odd case, last iteration, left == right
                left.next = right;
            }
            prev = right;
        }
    }
}