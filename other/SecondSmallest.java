/*
 * Return the second smallest integer in an array
 *
 * time: O(n), space: O(1)
 */

import java.util.*;

class SecondSmallest {
    public static int secondSmallest(int[] nums) {
	// assume input array is always valid and length >= 2
	int min = Integer.MAX_VALUE, secondMin= Integer.MAX_VALUE;
	for (int i : nums) {
	    if (i < min) {
		secondMin = min;
		min = i;
	    } else if (i < secondMin) {
		secondMin = i;
	    }
	}
	return secondMin;
    }

    public static void main(String[] args) {
	int[] nums = new int[]{4,3,6,8,5,9,10,5,1,7};
	int secondMin = secondSmallest(nums);
	System.out.println(secondMin);
    }
}
