/*
	Given an array nums of integers, you can perform operations on the array.

	In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

	You start with 0 points. Return the maximum number of points you can earn by applying such operations.

	Example 1:
	Input: nums = [3, 4, 2]
	Output: 6
	Explanation: 
	Delete 4 to earn 4 points, consequently 3 is also deleted.
	Then, delete 2 to earn 2 points. 6 total points are earned.
	Example 2:
	Input: nums = [2, 2, 3, 3, 3, 4]
	Output: 9
	Explanation: 
	Delete 3 to earn 3 points, deleting both 2's and the 4.
	Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
	9 total points are earned.
	Note:

	The length of nums is at most 20000.
	Each element nums[i] is an integer in the range [1, 10000].
 */

// Solution 1:
// Use a 2D array to store the two states at each index.
// First state is to include the current number,
// and the second state is to exclude the current number.
// We make choices at each index based on the relationship
// between current number and its previous number.
// Time: O(n)
// Space: O(n)
// 12/02/2017

class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) return 0;
        int[][] memo = new int[nums.length][2];    // [0] pick, [1] don't pick
        Arrays.sort(nums);
        memo[0][0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {   // same number as previous one
                memo[i][0] = memo[i-1][0] + nums[i];
                memo[i][1] = memo[i-1][1];
            } else if (nums[i] == nums[i-1]+1) {  // consecutive number
                memo[i][0] = memo[i-1][1] + nums[i];  // pick current number and exclude previous number
                memo[i][1] = Math.max(memo[i-1][0], memo[i-1][1]);            // don't pick current number and exclude previous number
            } else {
                memo[i][0] = Math.max(memo[i-1][1], memo[i-1][0]) + nums[i];
                memo[i][1] = Math.max(memo[i-1][1], memo[i-1][0]);
            }
        }
        return Math.max(memo[nums.length-1][1], memo[nums.length-1][0]);
    }
}