/*
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
 * (ie, from left to right, level by level from leaf to root).
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        traverse(result, root, 0); // recursively build the list
        return result;
    }
    
    public void traverse(List<List<Integer>> result, TreeNode node, int level) {
        if (node == null) return;   // base case
        if (level >= result.size()) {  // if the level does not exist, add it to the result list
            result.add(0, new ArrayList<>());
        }
        traverse(result, node.left, level+1);   // recursive call on left child
        traverse(result, node.right, level+1);  // recursive call on right child
        result.get(result.size()-level-1).add(node.val);    // add the node to its level
    }
}

/*
// first do level order traversal, then reverse the list
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) return result;
        
        // first do regular level order traversal
        queue.offer(root);
        List<Integer> level = new ArrayList<>();
        
        int preCount = 1;  // number of nodes in previous level
        int count = 0;     // number of nodes in current level
        
        while (queue.size() != 0) {
            TreeNode cur = queue.poll();
            preCount--;   
            level.add(cur.val);
            
            if (cur.left != null) {
                queue.offer(cur.left);
                count++;
            }
            
            if (cur.right != null) {
                queue.offer(cur.right);
                count++;
            }
            
            if (preCount == 0) {    // we have checked all nodes in previous level
                result.add(level);  // add the current level to the result
                level = new ArrayList<>();
                preCount = count;   // move on to next level
                count = 0;
            }
        }
        
        // Reverse the result list
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i = result.size()-1; i >= 0; i--) {
            res.add(result.remove(i));
        }
    
        return res;
    }
}
*/