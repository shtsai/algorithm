/*
 * Given an array of N numbers, each time, we can increment 
 * N-1 numbers by 1. Return the min number of step to make
 * all numbers equal.
 */

import java.util.*;

class IncrementAllButOneUntilAllEqual {
    /* 
     * Solution 1:
     * Increment all numbers but one is equivalent to subtract
     * one number by 1.
     * Therefore, we just need to count how many steps we need
     * to reduce all numbers to the minimum value.
     * We can first find the minimum value in the array, then
     * sum up the difference between all numbers and the min.
     *
     * Time: O(n)
     * Space: O(1)
     */
    public static int minStepToAllEqual(int[] array) {
	int min = Integer.MAX_VALUE;
	for (int n : array) {
	    min = Math.min(min, n);
	}

	int count = 0;
	for (int n : array) {
	    count += n - min;
	}
	return count;
    }

    public static void displaySteps(int[] array, int steps) {
	for (int s = 0; s < steps; s++) {
	    int max = Integer.MIN_VALUE;
	    int maxIndex = -1;
	    for (int i = 0; i < array.length; i++) {
		if (array[i] > max) {
		    max = array[i];
		    maxIndex = i;
		}
	    }
	    
	    for (int i = 0; i < array.length; i++) {
		if (i == maxIndex) continue;
		array[i]++;
	    }

	    for (int n : array) {
		System.out.print(n + " ");
	    }
	    System.out.println();
	}
    }

    public static void main(String[] args) {
	int[] array = {1, 1, 1, 2, 2, 4, 3};
	System.out.println("Original array is :");
	for (int n : array) {
	   System.out.print(n + " ");
	}
	System.out.println();
	int res = minStepToAllEqual(array);
	System.out.println("The minmium number of steps to make all numbers equal is " + res);
	displaySteps(array, res);
    }
}
