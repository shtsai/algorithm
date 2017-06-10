/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
// Tree Level Traversal
public class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int count = 1, next = 0, max = root.val;
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            count--;
            if (p.left != null) {
                q.offer(p.left);
                next++;
            }
            if (p.right != null) {
                q.offer(p.right);
                next++;
            }
            max = Math.max(max, p.val);
            
            if (count == 0) {
                result.add(max);
                count = next;
                next = 0;
                max = Integer.MIN_VALUE;
            }
        }
        
        return result;
    }
}