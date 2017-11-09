/*
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * 
 *         _______3______
 *        /              \
 *     ___5__          ___1__
 *    /      \        /      \
 *    6      _2       0       8
 *          /  \
 *          7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
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

// Solution 2: Iterative solution
// Use a HashMap to store the parent node of each node.
// Then do BFS to find p and q, and record the ancestors along the way.
// Once we find p and q, put all p's ancestor into a set.
// Then traverse through q's ancestor, and return the first one that intersect with p's ancestor.
//
// Time: O(n)
// Space: O(n)
// 11/09/2017

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        parent.put(root, null);
        queue.offer(root);
        
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        } 
        Set<TreeNode> set = new HashSet<>();
        while (p != null) {     // add all parents to a set
            set.add(p);
            p = parent.get(p);
        }
        while (!set.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }
}

// Solution 1: Recursive solution
// Recursive search for LCA on the left and right subtree.
// If none of them is in a subtree, null will be returned.
// If both of them are in the same subtree, the LCA will be returned.
// If they are two two different subtree, then two nodes will be returned
// from left and right respectively. Therefore, the root is LCA.
//
// Time: O(n)
// Space: O(n)
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {   // base case
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);  // recursively find left and right
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;     // p and q are on two different subtrees
        else if (left != null) return left;
        else return right;
    }
}