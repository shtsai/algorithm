/*
	Given an integer array, find three numbers whose product is maximum and output the maximum product.

	Example 1:
	Input: [1,2,3]
	Output: 6
	Example 2:
	Input: [1,2,3,4]
	Output: 24
	Note:
	The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
	Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */

// Solution 2: Find max1, max2, max3, min1, and min2 in one pass
// Time: O(n)
// Space: O(1)
// 10/16/2017
	
class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE; 
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
            
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }
        
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}

// Solution 1: Sorting
// Sort the array to ascending order.
// There are two possible combinations for max product:
//		1. the three largest positive number
//		2. the two smallest negative number * the largest positive number
// Therefore, we only need to compare these two combinations.
// Time: O(nlogn) - sorting
// Space: O(1)
// 10/16/2017

class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length-1;
        return Math.max(nums[0]*nums[1]*nums[n],nums[n-2]*nums[n-1]*nums[n]);
    }
}