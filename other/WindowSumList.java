/*
 * Given a list and a window size, return a new list that contains the sum
 * of all numbers in the window. 
 */

import java.util.*;

class WindowSumList {
    public static void main(String[] args) {
	ArrayList<Integer> list1 = new ArrayList<>();
	list1.add(0);
	list1.add(1);
	list1.add(2);
	list1.add(3);
	list1.add(4);
	list1.add(5);
	list1.add(6);

	ArrayList<Integer> list2 = new ArrayList<>();
	list2.add(4);
	list2.add(2);
	list2.add(73);
	list2.add(11);
	list2.add(-5);

	long time1 = System.nanoTime();
	ArrayList<Integer> res1 = windowSumList(list1, 2);
	ArrayList<Integer> res2 = windowSumList(list1, 3);
	ArrayList<Integer> res3 = windowSumList(list2, 2);
	ArrayList<Integer> res4 = windowSumList(list2, 3);
	time1 = System.nanoTime() - time1;
	long time2 = System.nanoTime();
	ArrayList<Integer> res5 = windowSumList(list1, 2);
	ArrayList<Integer> res6 = windowSumList(list1, 3);
	ArrayList<Integer> res7 = windowSumList(list2, 2);
	ArrayList<Integer> res8 = windowSumList(list2, 3);
	time2 = System.nanoTime() - time2;

	System.out.println("Version 1:\nres1:");
	for (int i : res1) System.out.print(i + " ");
	System.out.println("\nres2:");
	for (int i : res2) System.out.print(i + " ");
	System.out.println("\nres3:");
	for (int i : res3) System.out.print(i + " ");
	System.out.println("\nres4:");
	for (int i : res4) System.out.print(i + " ");
	System.out.println("\n time is " + time1 + "\n");

	System.out.println("Version 2:\nres5:");
	for (int i : res5) System.out.print(i + " ");
	System.out.println("\nres6:");
	for (int i : res6) System.out.print(i + " ");
	System.out.println("\nres7:");
	for (int i : res7) System.out.print(i + " ");
	System.out.println("\nres8:");
	for (int i : res8) System.out.print(i + " ");
	System.out.println("\n time is " + time2);
    }

    /* 
     * version 1: start from the first index, add up all k numbers,
     * then repeat the process until cannot form k-window.
     */
    public static ArrayList<Integer> windowSumList(ArrayList<Integer> list, int k) {
	if (list == null || list.size() == 0) return new ArrayList<Integer>();
	ArrayList<Integer> res = new ArrayList<>();
	for (int i = 0; i < list.size()-k+1; i++) {
	    int sum = 0;
	    for (int j = i; j < k + i; j++) {
		sum += list.get(j);
	    }
	    res.add(sum);
	}
	return res;
    }
    
    /*
     * version 2: maintain a sum of the window. When window moves, 
     * remove the leftmost element, and add the new element.
     */
    public static ArrayList<Integer> windowSumList2(ArrayList<Integer> list, int k) {
	ArrayList<Integer> res = new ArrayList<>();
	if (list == null || list.size() == 0) return res;
	int firstSum = 0;
	for (int i = 0; i < k; i++) {
	    firstSum += list.get(i);
	}
	res.add(firstSum);
	for (int i = 1; i < list.size()-k+1; i++) {
	    res.add(res.get(i-1) - list.get(i-1) + list.get(i+k-1));
	}
	return res;
    }
}
