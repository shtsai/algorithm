/*
 * You are given a set of n types of rectangular 3-D boxes, 
 * where the i^th box has height h(i), width w(i) and depth d(i) (all real numbers). 
 * You want to create a stack of boxes which is as tall as possible, 
 * but you can only stack a box on top of another box if the dimensions of the 2-D base 
 * of the lower box are each strictly larger than those of the 2-D base of the higher box. 
 * Of course, you can rotate a box so that any side functions as its base. 
 * It is also allowable to use multiple instances of the same type of box.
 *
 * Solution: similar to Longest Increasing Subsequence
 * First get all three possible rotations of the box, and sort them in decreasing order
 * of base array. Then perform dynamic programming to find max height.
 *
 * Time: O(n^2)
 * Space: O(n)
 * 10/26/2017
 */

import java.util.*;

class BoxStacking {
    private static class Box implements Comparable<Box> {
	int l, w, h;

	public Box(int l, int w, int h) {
	    this.l = l;
	    this.w = w;
	    this.h = h;
	}

	/* return true if the other box can be placed on top of this box */
	public boolean canFit(Box other) {
	    return (other.l < this.l && other.w < this.w)
		   || (other.l < this.w && other.w < this.l);
	}

	/* sort in decreasing order of base area */
	public int compareTo(Box other) {
	    return other.l * other.w - this.l * this.w;
	}

	public String toString() {
	    return l + " " + w + " " + h + "\n";
	}
    }

    public static int stackbox(List<Box> boxes) {
	List<Box> allboxes = new ArrayList<>();
	for (Box b : boxes) {
	    allboxes.add(new Box(b.l, b.w, b.h));
	    allboxes.add(new Box(b.w, b.h, b.l));
	    allboxes.add(new Box(b.h, b.l, b.w));
	}
	Collections.sort(allboxes);	
	
	int[] dp = new int[allboxes.size()];
	for (int i = 0; i < allboxes.size(); i++) {
	    dp[i] = allboxes.get(i).h;
	}
	int max = 0;

	for (int i = 1; i < allboxes.size(); i++) {
	    Box current = allboxes.get(i);
	    for (int j = 0; j < i; j++) {
		Box pre = allboxes.get(j);
		if (pre.canFit(current)) {
		    dp[i] = Math.max(dp[i], dp[j] + current.h); 
		}
	    }
	    max = Math.max(max, dp[i]);
	}

	return max;
    }

    public static void main(String[] argv) {
	List<Box> boxes = new ArrayList<>();
	boxes.add(new Box(4,6,7));
	boxes.add(new Box(1,2,3));
	boxes.add(new Box(4,5,6));
	boxes.add(new Box(10,12,32));

	int height = stackbox(boxes);
	System.out.println("Maximum height of stack is " + height);
    }

}

