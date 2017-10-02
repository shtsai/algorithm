/*
    Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

    For example, given nums = [-2, 0, 1, 3], and target = 2.

    Return 2. Because there are two triplets which sums are less than 2:

    [-2, 0, 1]
    [-2, 0, 3]
 */

// Solution 1:
// Similar to 3 Sum
// Key step is how to add count
// Time: O(n^2)
// Space: O(1)
// 10/02/2017

class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i+1, right = nums.length-1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < target) {
                    // KEY STEP:
                    // since nums[i] + nums[left] + nums[right] < target
                    // if we decrement right, the sum will still be less than target
                    // The number of such pairs are (right - left)
                    count += right-left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return count;
    }
}