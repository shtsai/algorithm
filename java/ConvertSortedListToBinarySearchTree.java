/*
    Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

    For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

    Example:

    Given the sorted linked list: [-10,-3,0,5,9],

    One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

          0
         / \
       -3   9
       /   /
     -10  5 
 */

// Solution 2: In-order traversal
// Time: O(n)
// Space: O(logn) - stack
// 09/11/2018
class Solution {
    ListNode node;
    public TreeNode sortedListToBST(ListNode head) {
        ListNode p = head;
        int length = 0;
        while (p != null) {
            length++;
            p = p.next;
        }
        node = head;
        return generate(0, length - 1);
    }
    
    private TreeNode generate(int start, int end) {
        if (start > end) {  // base case
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode left = generate(start, mid - 1);
        TreeNode root = new TreeNode(node.val);
        node = node.next;
        TreeNode right = generate(mid + 1, end);
        root.left = left;
        root.right = right;
        return root;
    }
}

// Solution 1: Recursive
// Time: O(nlogn)
// Space: O(logn) - stack
// 09/11/2018

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head,null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){  
        if(head == tail) {
            return null;
        }

        // use a fast and slow pointer to find mid
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail){
            fast = fast.next.next;              // fast jumps two steps each time,
            slow = slow.next;                   // when fast reaches the end, slow is at the middle
        }
        TreeNode root = new TreeNode(slow.val);  
        root.left = toBST(head,slow);
        root.right = toBST(slow.next,tail);
        return root;
    }
}