/*
    Fringe - List of leaf nodes from left to right. Given 2 balanced binary trees, check if their fringes match

          9
       4     8
       Fringe - 4, 8
       
          9
       6      8
     2  
       Fringe = 2, 8

    Rubrik
    09/27/2017
 */

import java.util.*;

class CompareFringe {
    public static boolean compareFringe(TreeNode a, TreeNode b) {
        List<TreeNode> aList = getFringe(a);
        List<TreeNode> bList = getFringe(b);
        
	System.out.print("List a = ");
        for (int i = 0; i < aList.size(); i++) {
	    System.out.print(aList.get(i).val + " ");		
	}
	System.out.println();

	System.out.print("List b = ");
        for (int i = 0; i < bList.size(); i++) {
	    System.out.print(bList.get(i).val + " ");
	}
	System.out.println();
        
	if (aList.size() != bList.size()) return false;
        for (int i = 0; i < aList.size(); i++) {
            if (aList.get(i).val != bList.get(i).val) {
                return false;
            }
        }
        return true;
    }

    private static List<TreeNode> getFringe (TreeNode node) {
        List<TreeNode> res = new ArrayList<>();
        getFringeHelper(res, node);
        return res;
    }

    private static void getFringeHelper(List<TreeNode> res, TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) {  // find a leaf
            res.add(node);
            return;
        }
        
        getFringeHelper(res, node.left);
        getFringeHelper(res, node.right);
    }

    public static boolean compareFringe2(TreeNode a, TreeNode b) {
        Stack<TreeNode> aStack = new Stack<>();
        Stack<TreeNode> bStack = new Stack<>();
        aStack.push(a);
        bStack.push(b);
        
        while (!(aStack.isEmpty() || bStack.isEmpty())) {
            // get leaves
            TreeNode aLeaf = getLeaf(aStack);
            TreeNode bLeaf = getLeaf(bStack);
           
	    System.out.println("aLeaf = " + aLeaf.val + " bLeaf = " + aLeaf.val);

            if (aLeaf.val != bLeaf.val) {
                return false;
            }
        }
        
        return aStack.isEmpty() && bStack.isEmpty();
        
    }

    private static TreeNode getLeaf(Stack<TreeNode> stack) {
        if (stack.isEmpty()) {
            return null;
        }
        
        TreeNode node = stack.pop();
        
        while (node.left != null || node.right != null) {
            if (node.left != null) {
                if (node.right != null) {
                    stack.push(node.right);
                }
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    private static class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int value) {
	    val = value;
	}
    }

    public static void main(String[] args) {
	/*   Tree A:
	 *               1
	 *         2            3
	 *      4     5
	 *        6
	 *
	 *   Tree B:
	 *		 1
	 *	   2            7
	 *	4     5           3
	 *     6
	 */

	TreeNode a = new TreeNode(1);
	TreeNode A = a;
	a.left = new TreeNode(2);
	a.right = new TreeNode(3);
	a = a.left;
	a.left = new TreeNode(4);
	a.right = new TreeNode(5);
	a = a.left;
	a.right = new TreeNode(6);

	TreeNode b = new TreeNode(1);
	TreeNode B = b;
	b.left = new TreeNode(2);
	b.right = new TreeNode(7);
	b.right.right = new TreeNode(3);
	b = b.left;
	b.left = new TreeNode(4);
	b.right = new TreeNode(5);
	b = b.left;
	b.left = new TreeNode(6);

	System.out.println("Compare fringe using method 1: ");
	System.out.println(compareFringe(A, B));
	System.out.println("Compare fringe using method 2: ");
	System.out.println(compareFringe2(A, B));
    }
}
