/*
    Given a binary tree, return the postorder traversal of its nodes' values.

    For example:
    Given binary tree {1,#,2,3},
       1
        \
         2
        /
       3
    return [3,2,1].

    Note: Recursive solution is trivial, could you do it iteratively?
 */

// Solution 3: Iterative approach (reverse of the preorder traversal)
// Time: O(n)
// Space: O(n)
// 09/12/2018
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;    
        }
        Stack<TreeNode> stack = new Stack<>();  
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode p = stack.pop();
            result.add(0, p.val);    // insert the node to the front
            if (p.left != null) {    
                stack.push(p.left);       
            }
            if (p.right != null) {    
                stack.push(p.right);      
            }
        }
        return result;
    }
}

// Soltion 2: Iterative solution
// Use a stack and pre node.
// Time: O(n)
// Space: O(n)
// 11/20/2017
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.peek();
            if (pre == null || pre.left == current || pre.right == current) {
                if (current.left != null) {
                    stack.push(current.left);
                } else if (current.right != null) {
                    stack.push(current.right);
                } else {
                    res.add(current.val);
                    stack.pop();
                }
            } else if (current.left == pre) {
                if (current.right != null) {
                    stack.push(current.right);
                } else {
                    res.add(current.val);
                    stack.pop();
                }
            } else {
                res.add(current.val);
                stack.pop();
            }
            pre = current;
        }
        return res;
    }
}


// Solution 1: Recursive solution
// Time: O(n)
// Space: O(n) - call stack
// 11/20/2017
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }
    
    public void traverse(TreeNode root, List<Integer> result) {
        if (root == null) return;
        traverse(root.left, result);
        traverse(root.right, result);
        result.add(root.val);
        return;
    }
}
