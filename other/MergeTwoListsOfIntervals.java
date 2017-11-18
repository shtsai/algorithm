/*
 *  Given two lists of sorted, non-overlapping intervals,
 *  return a merged list of intervals in sorted order.
 */

// Solution 1:
// Similar to merge sort.
// Each iteration, we examine the head of two lists, and get the interval
// with a smaller start time. Then we add it the new list, merge it if necessary.
// Repeat until we process all elements.
//
// Time: O(m + n) - one pass
// Space: O(1)
// 11/17/2017

import java.util.*;

class MergeTwoListsOfIntervals {
    private static class Interval {
	int start, end;
	public Interval(int start, int end) {
	    this.start = start;
	    this.end = end;
	}

	public String toString() {
	    return this.start + "->" + this.end;
	}
    }
    public static List<Interval> merge(List<Interval> a, List<Interval> b) {
	List<Interval> res = new ArrayList<>();
	if (a.size() == 0 && b.size() == 0) return res;
	int ai = 0, bi = 0;
	res.add(new Interval(-1, -1));
	while (ai < a.size() || bi < b.size()) {
	    Interval pre = res.get(res.size()-1);
	    Interval an = ai < a.size() ? a.get(ai) : null;
	    Interval bn = bi < b.size() ? b.get(bi) : null;
	    Interval current;
	    if (an != null && bn != null) { // find current interval
		if (an.start < bn.start) {
		    current = an;
		    ai++;
		} else {
		    current = bn;
		    bi++;
		}
	    } else if (an != null) {
		current = an;
		ai++;
	    } else {
		current = bn;
		bi++;
	    }

	    if (pre.start == -1 && pre.end == -1) {
		pre.start = current.start;
		pre.end = current.end;
	    } else if (current.start <= pre.end) {
		pre.end = Math.max(pre.end, current.end);
	    } else {
		res.add(current);
		pre = current;
	    }
	}
	return res;
    }

    public static void main(String[] args) {
	List<Interval> intervals1 = new ArrayList<>();
	List<Interval> intervals2 = new ArrayList<>();
	intervals1.add(new Interval(1, 3));
	intervals1.add(new Interval(4, 6));
	intervals1.add(new Interval(8, 10));
	intervals2.add(new Interval(1, 2));
	intervals2.add(new Interval(5, 7));
	intervals2.add(new Interval(9, 10));
	List<Interval> res = merge(intervals1, intervals2);
	System.out.println("The first list is :");
	for (Interval i : intervals1) {
	    System.out.print(i + " ");
	}
	System.out.println();
	System.out.println("The second list is :");
	for (Interval i : intervals2) {
	    System.out.print(i + " ");
	}
	System.out.println();
	System.out.println("The merged result is :");
	for (Interval i : res) {
	    System.out.print(i + " ");
	}
	System.out.println();
	System.out.println();

	List<Interval> intervals3 = new ArrayList<>();
	List<Interval> intervals4 = new ArrayList<>();
	intervals3.add(new Interval(1, 3));
	intervals3.add(new Interval(6, 9));
	intervals4.add(new Interval(3, 6));
	List<Interval> res2 = merge(intervals3, intervals4);
	System.out.println("The first list is :");
	for (Interval i : intervals3) {
	    System.out.print(i + " ");
	}
	System.out.println();
	System.out.println("The second list is :");
	for (Interval i : intervals4) {
	    System.out.print(i + " ");
	}
	System.out.println();
	System.out.println("The merged result is :");
	for (Interval i : res2) {
	    System.out.print(i + " ");
	}
	System.out.println();
    }
}
