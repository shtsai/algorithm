/*
    Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

    The encoded string should be as compact as possible.

    Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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

// Solution 2: More compact, no "X"
// When deserializing, split the numbers into smaller and larger halves.
// Time: O(n^2)
// Space: O(n)
// 08/14/2018

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }
    
    private void preOrder(TreeNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val + ",");
            preOrder(root.left, sb);
            preOrder(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        Queue<Integer> nodes = new LinkedList<>();
        for (String s : data.split(",")) {
            nodes.offer(Integer.parseInt(s));
        }
        return helper(nodes);
    }
    
    private TreeNode helper(Queue<Integer> nodes) {
        if (nodes.size() == 0) return null;
        TreeNode root = new TreeNode(nodes.poll());
        Queue<Integer> smaller = new LinkedList<>();
        while (!nodes.isEmpty() && nodes.peek() < root.val) {
            smaller.offer(nodes.poll());
        }
        root.left = helper(smaller);
        root.right = helper(nodes);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


// Solution 1: Pre-order serialization
// Time: O(n) - serialize
//       O(n) - deserialize
// Space: O(n)
// 08/14/2018
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString();
    }
    
    private void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("X,");
        } else {
            sb.append(root.val + ",");
            preOrder(root.left, sb);
            preOrder(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
        return helper(nodes);
    }
    
    private TreeNode helper(Queue<String> nodes) {
        String cur = nodes.poll();
        if (cur.equals("X")) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(cur));
            root.left = helper(nodes);
            root.right = helper(nodes);
            return root;
        }
    }
}

