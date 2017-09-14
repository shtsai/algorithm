/*
 * Given an array and a window size, return an array that contains the sum
 * of all numbers in the window. 
 */

import java.util.*;

class WindowSumArray {
    public static void main(String[] args) {
	int[] nums1 = {1,2,3,4,5,6,7,8,9};
	int[] nums2 = {4,2,73,11,-5};

	int[] res1 = windowSum(nums1, 2);
	int[] res2 = windowSum(nums1, 3);
	int[] res3 = windowSum(nums2, 2);
	int[] res4 = windowSum(nums2, 3);
	
	System.out.println("\nres1:");
	for (int i : res1) System.out.print(i + " ");
	System.out.println("\nres2:");
	for (int i : res2) System.out.print(i + " ");
	System.out.println("\nres3:");
	for (int i : res3) System.out.print(i + " ");
	System.out.println("\nres4:");
	for (int i : res4) System.out.print(i + " ");
	System.out.println();
    }

    public static int[] windowSum(int[] nums, int k) {
	if (nums == null || nums.length == 0) return null;
	int[] res = new int[nums.length-k+1];
	for (int i = 0; i < k; i++) {	// initialize the first window sum
	    res[0] += nums[i];
	}
	for (int i = 1; i < nums.length-k+1; i++) {
	    res[i] = res[i-1] - nums[i-1] + nums[i+k-1];
	}
	return res;
    }
}
