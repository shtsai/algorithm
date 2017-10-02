/*
 * Given an array S of n integers, 
 * find three integers in S such that the sum is closest to a given number, target. 
 * Return the sum of the three integers. 
 * You may assume that each input would have exactly one solution.
 */

// Solution 1:  convert the problem into 2Sum
// First sort the array in ascending order
// Use a pointer to scan through the array and set goals
// Use another two pointers to do two sum searches
// Update closest and diff when we find a closer pair
// Time: O(n^2)
// Space: O(1)
// 10/02/2017

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int closest = Integer.MAX_VALUE, diff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int goal = target - nums[i];
            int left = i+1, right = nums.length-1;
            while (left < right) {
                if (nums[left]+nums[right] == goal) return target;
                if (Math.abs(nums[left]+nums[right]-goal) < diff) { // find a closer one
                    closest = nums[left] + nums[right] + nums[i];
                    diff = Math.abs(nums[left]+nums[right]-goal);
                }
                if (nums[left]+nums[right] < goal) {  // continue search
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closest;
    }
}