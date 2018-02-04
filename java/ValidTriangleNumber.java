/*
	Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
	Example 1:
	Input: [2,2,3,4]
	Output: 3
	Explanation:
	Valid combinations are: 
	2,3,4 (using the first 2)
	2,3,4 (using the second 2)
	2,2,3
	Note:
	The length of the given array won't exceed 1000.
	The integers in the given array are in the range of [0, 1000].
 */

// Solution 1: Three pointers
// One pointer points to the longest side.
// Another two pointers form a window to find valid pairs.
// If (side1 + side2) < longest, add all pairs (side2 - side1) to the counter.
//
// Time: O(n^2)
// Space: O(1)
// 02/04/2018

class Solution {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        Arrays.sort(nums);
        int longest = nums.length-1;
        while (longest > 1) {
            int side1 = 0, side2 = longest - 1;
            while (side1 < side2) {
                if (nums[side1] + nums[side2] > nums[longest]) {
                    count += (side2 - side1);
                    side2--;
                } else {
                    side1++;
                }
            }
            longest--;
        }
        return count;
    }
}