/*
	Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

	Note:

	Given target value is a floating point.
	You may assume k is always valid, that is: k ≤ total nodes.
	You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
	Example:

	Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

	    4
	   / \
	  2   5
	 / \
	1   3

	Output: [4,3]
	Follow up:
	Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
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

// Solution 4: Predecessor and Successor
// Use stacks to keep track of the parent node.
// Find predecesor and successor then only take O(logn) time
//
// Time: O(klogn) - k number in the pq, each predecessor and successor takes O(logn) 
// Space: O(k + logn)
// 08/09/2018

class Solution {
    private Stack<TreeNode> succStack;
    private Stack<TreeNode> predStack;
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        succStack = new Stack<TreeNode>();
        predStack = new Stack<TreeNode>();

        TreeNode closest = binarySearch(root, target);
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((Integer a, Integer b) -> {
            double aDiff = Math.abs(a - target);
            double bDiff = Math.abs(b - target);
            if (aDiff <= bDiff) {
                return -1;
            } else {
                return 1;
            }
        });
        minHeap.offer(closest.val);
        TreeNode predecessor = getPredecessor(closest); 
        TreeNode successor = getSuccessor(closest);
        while (minHeap.size() < k) {
            if (predecessor != null) {
                minHeap.offer(predecessor.val);
                predecessor = getPredecessor(predecessor);
            }
            if (successor != null) {
                minHeap.offer(successor.val);
                successor = getSuccessor(successor);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(minHeap.poll());
        }
        return res;
    }
    
    public TreeNode binarySearch(TreeNode root, double target) {
        TreeNode closest = null;
        double minDiff = Double.MAX_VALUE;
        
        while (root != null) {
            if (Math.abs(root.val - target) < minDiff) {
                minDiff = Math.abs(root.val - target);
                closest = root;
            }
            predStack.push(root);
            succStack.push(root);
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        
        while (predStack.peek() != closest) {
            predStack.pop();
            succStack.pop();
        }
        
        return closest;
    }
    
    public TreeNode getPredecessor(TreeNode node) {
        if (node == null) {
            return null;
        } else if (node.left != null) {
            node = node.left;
            while (node.right != null) {
                predStack.push(node);
                node = node.right;
            }
            return node;
        } else {
            while (!predStack.isEmpty()) {
                TreeNode parent = predStack.pop();
                if (parent.val < node.val) {
                    return parent;
                }
            }
            return null;
        } 
    }
    
    public TreeNode getSuccessor(TreeNode node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                succStack.push(node);
                node = node.left;
            }
            return node;
        } else {
            while (!succStack.isEmpty()) {
                TreeNode parent = succStack.pop();
                if (parent.val > node.val) {
                    return parent;
                }
            }
            return null;
        }
    }
}

// Solution 3: Predecessor and Successor
// Get all predecessors and successors of target by doing inorder traversal.
// Then get result in a mergesort style.
// Time: O(n)
// Space: O(n)
// 08/09/2018
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<Integer> predecessor = new Stack<>();
        Stack<Integer> successor = new Stack<>();
        inorder(root, target, predecessor, true);   // get all number smaller than target in a stack
        inorder(root, target, successor, false);    // get all number greater than target in a stack
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (predecessor.isEmpty()) {
                res.add(successor.pop());
            } else if (successor.isEmpty()) {
                res.add(predecessor.pop());
            } else if (Math.abs(successor.peek() - target) < Math.abs(predecessor.peek() - target)) {
                res.add(successor.pop());
            } else {
                res.add(predecessor.pop());
            }
        }
        return res;
    }
    
    private void inorder(TreeNode root, double target, Stack<Integer> stack, boolean leftFirst) {
        if (root == null) 
            return;
        inorder(leftFirst ? root.left : root.right, target, stack, leftFirst);
        
        // Stop when have all predecessors or successors
        if ((leftFirst && root.val > target) || (!leftFirst && root.val <= target)) {
            return;
        } else {
            stack.push(root.val);
        }
        
        inorder(leftFirst ? root.right : root.left, target, stack, leftFirst);
    }
}

// Solution 2: 
// Get sorted numbers, find 2k numbers near target
// Use a priorityqueue to find top k
//
// Time: O(n + klogk)
// Space: O(n + k)
// 08/07/2018
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> sortedNums = new ArrayList<>();
        inorderTraverse(root, sortedNums);
        
        int closestIndex = -1; 
        double minDiff = Double.MAX_VALUE;
        for (int i = 0; i < sortedNums.size(); i++) {
            int n = sortedNums.get(i);
            if (Math.abs(n - target) < minDiff) {
                minDiff = Math.abs(n - target);
                closestIndex = i;
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a, Integer b) -> {
            double diff1 = Math.abs(a - target);
            double diff2 = Math.abs(b - target);
            if (diff1 <= diff2) {
                return -1;
            } else {
                return 1;
            }
        });
        
        int left = closestIndex - 1, right = closestIndex + 1;
        pq.offer(sortedNums.get(closestIndex));
        while (pq.size() <= 2 * k && (left >= 0 || right < sortedNums.size())) {
            if (left >= 0) {
                pq.offer(sortedNums.get(left));
                left--;
            }
            if (right < sortedNums.size()) {
                pq.offer(sortedNums.get(right));
                right++;
            }
        }
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(pq.poll());
        }
        return res;
    }
    
    private void inorderTraverse(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.left, res);
        res.add(root.val);
        inorderTraverse(root.right, res);
    }
}

// Solution 1: Priority queue
// Scan through all numbers, and sort according to diff.
// 
// Time: O(nlogk)
// Space: O(n)
// 08/04/2018

class Solution {
    private class NodeDetail implements Comparable<NodeDetail> {
        int val;
        double diff;
        public NodeDetail(int val, double diff) {
            this.val = val;
            this.diff = diff;
        }
        
        @Override
        public int compareTo(NodeDetail other) {
            if (this.diff < other.diff) {
                return -1;
            } else if (this.diff > other.diff) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<NodeDetail> minHeap = new PriorityQueue<>();
        traverse(root, minHeap, target);
        
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(minHeap.poll().val);
        }
        return res;
    }
    
    private void traverse(TreeNode root, PriorityQueue<NodeDetail> minHeap, double target) {
        if (root == null) {
            return;
        }
        minHeap.offer(new NodeDetail(root.val, Math.abs(root.val - target)));
        traverse(root.left, minHeap, target);
        traverse(root.right, minHeap, target);
    }
}