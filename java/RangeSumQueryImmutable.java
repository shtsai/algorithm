/*
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * 
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * 
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 */
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

// Solution 1: preSum approach
// preSum[i] = the sum of all numbers from index 0 to index i
// Time: O(1)
// Space: O(n)
// 09/08/2018
class NumArray {
    int[] preSum;
    public NumArray(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
        int delete = i - 1 >= 0 ? preSum[i - 1] : 0;
        return preSum[j] - delete;
    }
}