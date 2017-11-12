/*
	Given an array of integers nums, write a method that returns the "pivot" index of this array.

	We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.

	If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.

	Example 1:
	Input: 
	nums = [1, 7, 3, 6, 5, 6]
	Output: 3
	Explanation: 
	The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
	Also, 3 is the first index where this occurs.
	Example 2:
	Input: 
	nums = [1, 2, 3]
	Output: -1
	Explanation: 
	There is no index that satisfies the conditions in the problem statement.
	Note:

	The length of nums will be in the range [0, 10000].
	Each element nums[i] will be an integer in the range [-1000, 1000].
 */

// Solution 1: two arrays
// Use one array to record the sum of everything to left of the current index.
// Use another array to record the sum of everything to right of the current index.
// Then for a given index, check if the sums on its left and right are the same.
// Note the indexing problem, need to handle left most left, and right most right.
//
// Time: O(n)
// Space: O(n)
// 11/11/2017

class Solution {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 2) return -1;
        if (nums.length == 1) return 0;
        
        int[] left = new int[nums.length+1];
        int[] right = new int[nums.length+1];
        left[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            left[i+1] = left[i] + nums[i];
        }
        
        right[nums.length] = 0;
        for (int i = nums.length-1; i >= 0; i--) {
            right[i] = right[i+1] + nums[i];
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (left[i] == right[i+1]) {
                return i;
            }
        }
        return -1;
    }
}