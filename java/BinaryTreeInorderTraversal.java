/*
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * For example:
 * Given binary tree [1,null,2,3],
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [1,3,2].
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Recursive solution
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        helper(root, list);
        return list;
    }
    
    public void helper(TreeNode node, List<Integer> list) {
        if (node == null) { return; }
        helper(node.left, list);
        list.add(node.val);
        helper(node.right, list);
        return;
    }
}

/*
// iterative solution
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<Integer>();
        
        while (cur != null || !stack.empty()) {
            while (cur != null) {   // go to the left most node
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();      // cur is null at this point, pop from stack
            list.add(cur.val);      // add the value of cur to the list
            cur = cur.right;        // then, move on to the right node
                                    // at this point, if cur.right is not null, 
                                    // the inner while loop will again try to go as left as possible,
                                    // otherwise, we will skip the inner while loop and pop from the stack
        }
    
        return list;
    }
}
*/