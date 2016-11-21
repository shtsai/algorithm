/*
 * Given a complete binary tree, count the number of nodes.
 * 
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, 
 * is completely filled, and all nodes in the last level are as far left as possible. 
 * It can have between 1 and 2h nodes inclusive at the last level h.
 */

public class Solution {
    // divide and conquer
    // T(n) = T(n/2) + log(n)
    // T(n) = O(log(n) ^ 2)
    public int countNodes(TreeNode root) {
        int result = 0;
        int h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) { // last node in right subtree
                result += 1 << (h - 1);        // left subtree is complete, add them all
                root = root.right;
            } else {                           // last node in left subtree
                result += 1 << (h - 2);        // right subtree is complete, add them
                root = root.left;
            }
            h--;                               // reduce height by 1
        }
        return result;
    }
    
    public int height(TreeNode root) {
        int h = 0;
        while (root != null) {
            h++;
            root = root.left;
        }
        return h;
    }

    /* Exceed Time limit
    // brute force, do tree walk
    public int countNodes(TreeNode root) {
        return traverse(root);
    }
    
    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = 0;
        result += traverse(root.left);
        result += traverse(root.right);
        return result + 1;
    }
    */
}