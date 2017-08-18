/*
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 */

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

// use three pointers:
// cur: points to the node we are currently processing
// head: points to the head of the list on next level, which is where to start after finishing current level
// tail: points to the tail of the list on next level, so that we can append to it
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        
        TreeLinkNode cur = root, head = null, tail = null;
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {     // left child
                    if (head == null) {     // haven't found the head of current level
                        head = cur.left;
                        tail = cur.left;
                    } else {
                        tail.next = cur.left;  // append left child to the tail
                        tail = tail.next;
                    }
                }
                
                if (cur.right != null) {
                    if (head == null) {
                        head = cur.right;
                        tail = cur.right;
                    } else {
                        tail.next = cur.right;
                        tail = tail.next;
                    }
                }
                cur = cur.next;     // handle next node at the current level
            }
            
            // finish all nodes at the current level, move on to next
            cur = head;
            head = null;
            tail = null;
        }
        
        return;
    }
}