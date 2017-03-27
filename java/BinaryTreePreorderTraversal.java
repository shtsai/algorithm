/*
 * Given a binary tree, return the preorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree {1,#,2,3},
 *    1
 *     \
 *      2
 *     /
 *    3
 * return [1,2,3].
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

// recursive solution
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        helper(root, list);
        return list;
    }
    
    public void helper(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        helper(node.left, list);
        helper(node.right, list);
        return;
    }
}

/*
// iterative solution
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        
        while (cur != null) {
            list.add(cur.val);           // add value to the list
            if (cur.right != null) {     // save the right node in the stack
                stack.push(cur.right);
            }
            cur = cur.left;              // move to the left
            if (cur == null && !stack.empty()) {
                cur = stack.pop();
            }
        }
        
        return list;
    }
}
*/