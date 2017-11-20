/*
    Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

    If two nodes are in the same row and column, the order should be from left to right.

    Examples:

    Given binary tree [3,9,20,null,null,15,7],
       3
      /\
     /  \
     9  20
        /\
       /  \
      15   7
    return its vertical order traversal as:
    [
      [9],
      [3,15],
      [20],
      [7]
    ]
    Given binary tree [3,9,8,4,0,1,7],
         3
        /\
       /  \
       9   8
      /\  /\
     /  \/  \
     4  01   7
    return its vertical order traversal as:
    [
      [4],
      [9],
      [3,0,1],
      [8],
      [7]
    ]
    Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
         3
        /\
       /  \
       9   8
      /\  /\
     /  \/  \
     4  01   7
        /\
       /  \
       5   2
    return its vertical order traversal as:
    [
      [4],
      [9,5],
      [3,0,1],
      [8,2],
      [7]
    ]

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

// Solution 1: BFS
// Do BFS to scan the tree level by level.
// We use another queue to keep track of the column number of each node.
// The column number of root is 0, and +1 for the right, -1 for the left.
// While we visit each node, add each node into its corresponding list
// by using its column index to search in the hashmap.
// 
// Time: O(n) - n is the number of nodes
// Space: O(n)
// 11/19/2017

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> nq = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        nq.offer(root);
        cols.offer(0);
        int max = 0, min = 0;
        while (!nq.isEmpty()) {
            TreeNode cur = nq.poll();
            int col = cols.poll();
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(cur.val);
            if (cur.left != null) {
                nq.offer(cur.left);
                cols.offer(col-1);
                min = Math.min(min, col - 1);
            }
            if (cur.right != null) {
                nq.offer(cur.right);
                cols.offer(col+1);
                max = Math.max(max, col + 1);
            }
        }
        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}