/*
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 * A:          a1 → a2
 *                   ↘
 *                     c1 → c2 → c3
 *                   ↗            
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 *
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 * 
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode tailA = headA;
        ListNode tailB = headB;
        while (tailA.next != null) {
            tailA = tailA.next;
        }
        while (tailB.next != null) {
            tailB = tailB.next;
        }
        if (tailA != tailB) {   // check if the tails of A and B are the same
            return null;        // if not, then two lists have no intersection
        }
        
        // create a cycle
        tailA.next = headA;
        
        // start from headB, find the starting point of the cycle
        ListNode fast = headB;
        ListNode slow = headB;
        
        do {        // use fast and slow pointer method to find meeting point
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        
        ListNode intersection = headB;
        while (intersection != slow) {  // then walk from headB to find the intersection
            intersection = intersection.next;
            slow = slow.next;
        }
        
        // restore list A
        tailA.next = null;
        
        return intersection;
    }
}