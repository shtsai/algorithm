/*
 *  Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * 
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example,
 * Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5 
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
// Use helper function
// Time: O(n)
// Space: O(1)
// 02/02/2018

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;
        while (prev != null) {
            prev = reverseAndReturnNextPrev(prev, k);
        }
        
        return dummy.next;
    }
       
    // VISUALIZATION: 
    // prev -> n1 -> n2 ... nk -> nk+1
    // =>
    // prev -> nk -> nk-1 .. n1 -> nk+1
    // return n1
    private ListNode reverseAndReturnNextPrev(ListNode prev, int k) {
        ListNode lastPrev = prev;
        ListNode n1 = prev.next;
        ListNode nk = prev;
        
        for (int i = 0; i < k; i++) {   
            nk = nk.next;
            if (nk == null) {   // less than k nodes left, unable to reverse
                return null;
            }
        }
        ListNode nkplus = nk.next;
        ListNode current = n1;
        for (int i = 0; i < k; i++) {    // reverse k-group
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        lastPrev.next = nk;
        n1.next = nkplus;
        
        return n1;
    }
}

// Solution 2:
// reverse K group, no extra memory needed
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        // start is the starting point of a K-group, end is the end point
        ListNode dummy = new ListNode(0), start = dummy, end = dummy;
        dummy.next = head;      // use a dummy head to keep the head of the list
        
        while (true) {
            int count = 0;
            while (count < k && end != null) {
                end = end.next;
                count++;
            }
            if (end == null) break;     // less than k nodes left, no enough for an new group
             
            // after reverse, start.next will be the last element 
            // that is where we will continue in next round
            ListNode nextStart = start.next;

            while (start.next != end) {      // reverse this K-group
                ListNode toMove = start.next;   // get the first node
                start.next = toMove.next;   // remove first node
                toMove.next = end.next;
                end.next = toMove;
            }
            start = nextStart;      // repeat the same operation on next group
            end = nextStart;
        }
        return dummy.next;
    }
}

// Solution 1:
// break list in to K groups, reverse each group
// then reconnect each group
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head, p = head, next = null, res = null;
        int count = 0, len = 0;
        Queue<ListNode> newHeads = new LinkedList<>();
        while (p != null) {    // break list into K groups and reverse each group
            count++;
            len++;
            ListNode pre = p;
            p = p.next;
            
            if (count == k) {
                pre.next = null;    // break link
                ListNode newHead = reverse(start);
                newHeads.offer(newHead);
                
                start = p;     // reset start and count
                count = 0;
            } else if (p == null && count < k) {    // cannot get k-group, keep the original order
                newHeads.offer(start);
            }
        }
        if (len < k) return head;

        // reconnect each group
        ListNode newHead = newHeads.peek();
        while (!newHeads.isEmpty()) {
            ListNode node = newHeads.poll();
            while (node.next != null) node = node.next;
            if (!newHeads.isEmpty()) node.next = newHeads.peek();
        }
        return newHead;
        
    }
    
    // helper function to reverse a linkedlist, return new head
    public ListNode reverse(ListNode head) {
        ListNode pre = null, p = head;
        while (p != null) {
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        return pre;
    }
}