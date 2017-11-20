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
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;    
        Deque<TreeNode> q = new ArrayDeque<>();  // use stack to store parent node
        TreeNode p = root;
        while (!q.isEmpty() || p != null) {
            if (p != null) {
                result.add(0, p.val);  // insert the node to the front
                q.push(p);             // save the parent node, whose right child is visited
                p = p.right;           // move to its right node first
            } else {                   // if the current node is invalid
                p = q.pop().left;      // pop to get parent node and traverse its left
            }
        }
        return result;
    }
}

// Soltion 2: Iterative solution
// Use a stack and pre node.
// Time: O(n)
// Space: O(logn)
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
// Space: O(logn) - call stack
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
