/*
	Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
	 
	We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

	Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/

// Solution 2: Iterative + stack
// Same idea as solution 1, except iterative
// Time: O(n)
// Space: O(n)
// 09/10/2018
class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node(-1, null, null);
        Node prev = dummy, cur = root;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            prev.right = cur;
            cur.left = prev;
            prev = cur;
            cur = cur.right;
        }
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;       
    }
}

// Solution 1: Inorder traversal - recursive
// Use a dummy node to hold the head.
// Then do inorder traveral to connect all the nodes.
// Time: O(n)
// Space: O(n) - stack space in worst case (skewed tree)
// 09/10/2018

class Solution {
    Node prev;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node(-1, null, null);
        prev = dummy;
        inorder(root);
        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;
        
    }
    private void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        prev.right = node;
        node.left = prev;
        prev = node;
        inorder(node.right);
    }
}