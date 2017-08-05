/*
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
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
// Divide and conquer (better performance, less memory used)
// recurvisely divide the problem into two halves, 
// solve by brute force when subproblem is small enough (0, 1 or 2 lists)
public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        return mergeHelper(lists, 0, lists.length-1);
    }
    
    public ListNode mergeHelper(ListNode[] lists, int start, int end) {     // recursively merge
        if (start > end) return null;
        if (start == end) return lists[start];
        if (end - start + 1 == 2) return mergeTwo(lists[start], lists[end]);
        int mid = start + (end - start) / 2;
        ListNode a = mergeHelper(lists, start, mid);
        ListNode b = mergeHelper(lists, mid + 1, end);
        return mergeTwo(a, b);
    }
    
    public ListNode mergeTwo(ListNode x, ListNode y) {    // merge two sorted lists
        ListNode head = new ListNode(0), p = head;
        while (x != null && y != null) {
            if (x.val < y.val) {
                p.next = new ListNode(x.val);
                p = p.next;
                x = x.next;
            } else {
                p.next = new ListNode(y.val);
                p = p.next;
                y = y.next;
            }
        }
        while (x != null) {
            p.next = new ListNode(x.val);
            p = p.next;
            x = x.next;
        }
        while (y != null) {
            p.next = new ListNode(y.val);
            p = p.next;
            y = y.next;
        }
        return head.next;
    }
}

// Solution 1:
// create a class to store (val, listIndex) pairs
// use a priority queue to efficiently obtain the min among heads of lists
public class Solution {
    private class Entry implements Comparable<Entry>{
        int val;
        int listIndex;
        public Entry(int x, int y) {
            val = x;
            listIndex = y;
        }   
        public int compareTo(Entry other) {
            return this.val - other.val;
        }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
      
        ListNode head = new ListNode(0), p = head;    
        PriorityQueue<Entry> q = new PriorityQueue<>();     // initialize priority queue
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                q.offer(new Entry(lists[i].val, i));
                lists[i] = lists[i].next;
            }
        }
        
        while (!q.isEmpty()) {
            Entry min = q.poll();
            p.next = new ListNode(min.val);
            p = p.next;
            if (lists[min.listIndex] != null) {     // has not reached end
                q.offer(new Entry(lists[min.listIndex].val, min.listIndex));
                lists[min.listIndex] = lists[min.listIndex].next;
            }
        }
        
        return head.next;
    }
}