/*
 * Given a binary tree, return the postorder traversal of its nodes' values
 */
public class Solution {

    //  Recursive approach
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }
    
    public void traverse(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        traverse(root.left, result);
        traverse(root.right, result);
        result.add(root.val);
        return;
    }

    /* Iterative approach (reverse of the preorder traversal)
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
    */
}