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

// Solution 3: two pointers
// e.g. 
//      list A = 1, 3, 5, 7, 9, 11
//               \        /  \   /
//             (a unique #)  (n intersected #)
//
//      list B = 2, 4, 9, 11
//               \  /   \  /
//       (b unique #)  (n intersected #)
// 
// If we combine the two number, we will get:
//      list A' =  1, 3, 5, 7, 9, 11, 2, 4, 9, 11
//                \                       /
//                        a + n + b
//
//      list B' =  2, 4, 9, 11, 1, 3, 5, 7, 9, 11
//                \                       /
//                        b + n + a
//
// Therefore, the intersection will be find after we scan 
// through the first (a + n + b) numbers.
// So we can use two pointers to scan through the two lists simultaneously.
// When we reaches the end of a list for the first time,
// we continue scanning the other list, until we find the 
// intersection or there is no more nodes to scan.
//
// Time: O(m + n)
// Space: O(1)
// 10/20/2017

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headA, q = headB;
        boolean firstA = true, firstB = true;
        
        while (p != q) {
            if (p.next != null) {
                p = p.next;
            } else if (firstA) {   // finish scanning list a, 
                p = headB;         // continue with list b
                firstA = false;
            } else {
                return null;  // no intersection
            }
            
            if (q.next != null) {
                q = q.next;
            } else if (firstB) {
                q = headA;
                firstB = false;
            } else {
                return null;
            }
        }
        
        return p;
    }
}


// Solution 2: Detect cycle
// Convert the problem to detecting cycle in linkedlist.
// Assuming we are allowed to modify input lists.
// Use fast and slow pointer to find starting node of the cycle.
// Time: O(m + n)
// Space: O(1)

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


// Solution 1: hashset
// First travere list A and add all nodes to a hashset
// Then traverse list B. If the node is the set, we find intersection.
// Time: O(m + n) - m: len(listA), n: len(listB)
// Space: O(m)
// 10/20/2017

class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode p = headA;
        while (p != null) {
            set.add(p);
            p = p.next;
        }
        
        p = headB;
        while (p != null) {
            if (set.contains(p)) {
                return p;
            }
            p = p.next;
        }
        
        return null;
    }
}
