/*
 * One way to serialize a binary tree is to use pre-order traversal. 
 * When we encounter a non-null node, we record the node's value. 
 * If it is a null node, we record using a sentinel value such as #.
 * 
 *        _9_
 *       /   \
 *      3     2
 *     / \   / \
 *    4   1  #  6
 *   / \ / \   / \
 *   # # # #   # #
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", 
 * where # represents a null node.
 *
 * Given a string of comma separated values, verify whether it is a correct preorder traversal 
 * serialization of a binary tree. Find an algorithm without reconstructing the tree.
 *
 * Each comma separated value in the string must be either an integer or a character '#' representing 
 * null pointer.
 *
 * You may assume that the input format is always valid, 
 * for example it could never contain two consecutive commas such as "1,,3".
 */

public class Solution {
    // use stack to keep track of parent nodes
    // "#"" represents a null node or a placeholder for a complete subtree
    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] nodes = preorder.split(",");
        
        for (String node : nodes) {
            // if the top of the stack is "#", it means the corresponding left subtree has completed
            while (node.equals("#") && !stack.isEmpty() && stack.peek().equals("#")) {
                stack.pop();  // remove the left #
                if (stack.isEmpty()) return false; // stack should not be empty
                stack.pop();  // remove the parent node of left subtree and right subtree
            }
            stack.push(node);  // push the node to the stack
            // if we went through the pop processes above, we still need to 
            // push "#" to the stack as a placeholder for the subtree we removed
        }
        
        // if the serialization is valid, the remaining element in the stack
        // should be a single "#"
        return stack.peek().equals("#") && stack.size() == 1;
    }


    /* FASTER SOLUTION
    // by the special property of the number of tree edges
    // the difference between number of incoming edges and the number of outgoing edges is always 1.
    // (one more outgoing edge)
    public boolean isValidSerialization(String preorder) {
        int diff = 1;   // initialize diff to 1
                        
        String[] nodes = preorder.split(",");
        for (String node : nodes) {
            diff--;     // one mode incoming edge
            if (diff < 0) return false;   // diff should not be lower than 0
            if (!node.equals("#")) {
                diff += 2;  // a node has two outgoing edges, while a null node has none
            }
        }
        
        return diff == 0;  // the difference should be 0 for a valid tree
    }
    */
}