/*
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

// Solution 1:
// use stack to store the parent nodes
// using stack meets the requirement of O(h) space
// next() is approximately O(1) time
// reference: https://discuss.leetcode.com/topic/6575/my-solutions-in-3-languages-with-stack

public class BSTIterator {
    private Stack<TreeNode> stack;
        
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        addAllLeftChild(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();	// has next if the stack is not empty
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode nextNode = stack.pop();	// the number on the top of stack is result
        addAllLeftChild(nextNode.right);	// before return, add its right subtree to the stack
        return nextNode.val;
    }
    
    /** @add all left children to the stack */
    private void addAllLeftChild(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
