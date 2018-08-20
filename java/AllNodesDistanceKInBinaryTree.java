/*
    We are given a binary tree (with root node root), a target node, and an integer value K.
    Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

    Example 1:
    Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
    Output: [7,4,1]
    Explanation:
    The nodes that are a distance 2 from the target node (with value 5)
    have values 7, 4, and 1.

    Note that the inputs "root" and "target" are actually TreeNodes.
    The descriptions of the inputs above are just serializations of these objects.

    Note:
    The given tree is non-empty.
    Each node in the tree has unique values 0 <= node.val <= 500.
    The target node is a node in the tree.
    0 <= K <= 1000.
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

// Solution 1: DFS + BFS
// Find parents of the target node
// Then do a BFS from the target node
//
// Time: O(n)
// Space: O(n)
// 08/17/2018

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        findParents(root, target, parents);
        List<Integer> res = new ArrayList<>();
        bfs(target, parents, K, res);
        return res;
    }

    private void findParents(TreeNode root, TreeNode target, HashMap<TreeNode, TreeNode> parents) {
        if (root == null || root == target) {
            return;
        }
        if (root.left != null) {
            parents.put(root.left, root);
            findParents(root.left, target, parents);
        }
        if (root.right != null) {
            parents.put(root.right, root);
            findParents(root.right, target, parents);
        }
    }

    private void bfs(TreeNode root, HashMap<TreeNode, TreeNode> parents, int K, List<Integer> res) {
        HashSet<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                TreeNode p = q.poll();
                visited.add(p);
                if (K == 0) {
                    res.add(p.val);
                }
                if (parents.containsKey(p) && !visited.contains(parents.get(p))) {
                    q.offer(parents.get(p));
                }
                if (p.left != null && !visited.contains(p.left)) {
                    q.offer(p.left);
                }
                if (p.right != null && !visited.contains(p.right)) {
                    q.offer(p.right);
                }
                size--;
            }
            K--;
        }
    }
}
