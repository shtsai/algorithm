/*
 * Given a singly linked list, determine if it is a palindrome.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// Solution 2 version 2:
// Break list into two parts, easier for the reverse
// 09/26/2017
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) { // find center
            slow = slow.next;
            fast = fast.next.next;
        }
        
        ListNode p = slow.next;
        slow.next = null;     // break the list into two halves
        ListNode pre = null;
        while (p != null) {   // reverse second half
            ListNode next = p.next;
            p.next = pre;
            pre = p;
            p = next;
        }
        
        ListNode q = head;       
        while (pre != null && q != null) {  // compare node by node
            if (pre.val != q.val) {
                return false;
            }
            pre = pre.next;
            q = q.next;
        }
        
        return true;
    }
}

// Solution 2:
// fast and slow pointer, find the middle of the list,
// reverse the second half, and check if it matches the first half
// Time: O(n)
// Space: O(1)
public class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {  // fast and slow pointer
            slow = slow.next;        // when fast reaches end, slow will be at the middle
            fast = fast.next.next;   
        }
        
        // reverse the second half of the linked list
        ListNode tail = null;
        ListNode current = slow;
        while (current != null) { 
            ListNode next = current.next;
            current.next = tail;
            tail = current;
            current = next;
        }
        
        ListNode node = head;
        while (tail != null && node != null) {
            if (tail.val != node.val) {
                return false;
            } else {
                tail = tail.next;
                node = node.next;
            }
        }
        
        return true;
        
    }
}

// Solution 1 version 2:
// Use ArrayList to dynamicly resize, don't need to know size in advance
// 09/26/2017
class Solution {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        int i = 0, j = list.size()-1;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

// Solution 1:
// first convert linked list to array, then check if it is palindrome
// Time: O(n), Space: O(n)
public class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode node = head;
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        
        int[] nums = new int[len];
        node = head;
        int i = 0;
        while (node != null) {
            nums[i] = node.val;
            i++;
            node = node.next;
        }
        
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            if (nums[start] != nums[end]) {
                return false;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }
}
