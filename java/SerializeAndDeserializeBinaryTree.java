/*
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following tree
 * 
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * 
 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * 
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless. 
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
// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

// Solution 2
// BFS and queue
// reference: https://discuss.leetcode.com/topic/31264/short-and-straight-forward-bfs-java-code-with-a-queue
public class Codec {

    // Encodes a tree to a single string. by BFS
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // level order traversal
        while (!q.isEmpty()) {
            TreeNode p = q.poll();
            if (p == null) {
                sb.append("null,");
                continue;
            }
            sb.append(p.val + ",");     // each valid node has two children
            q.offer(p.left);
            q.offer(p.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] dataArray = data.split(",");
        Queue<TreeNode> parents = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(dataArray[0]));
        parents.offer(root);
        for (int i = 1; i < dataArray.length; i++) {
            TreeNode parent = parents.poll();         // restore two children for the parent
            if (!dataArray[i].equals("null")) {     // add left node
                parent.left = new TreeNode(Integer.parseInt(dataArray[i]));
                parents.offer(parent.left);
            }
            i++;
            if (!dataArray[i].equals("null")) {     // add right node
                parent.right = new TreeNode(Integer.parseInt(dataArray[i]));
                parents.offer(parent.right);
            }
        }
        return root;
    }
}


// Solution 1:
// recursive approach based on preorder traversal and queue
// reference: https://discuss.leetcode.com/topic/28029/easy-to-understand-java-solution
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    // recursive helper to build a string based on preorder traversal
    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null");
            sb.append(",");
        } else {
            sb.append(root.val);
            sb.append(",");
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(q);
    }
    
    private TreeNode deserializeHelper(Queue<String> q) {
        String s = q.poll();
        if (q.isEmpty() || s.equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left =deserializeHelper(q);
        root.right = deserializeHelper(q);
        return root;
    }     
}