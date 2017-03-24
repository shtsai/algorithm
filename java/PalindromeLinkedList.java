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

// fast and slow pointer, find the middle of the list,
// reverse the second half, and check if it matches the first half
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

/*
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
*/