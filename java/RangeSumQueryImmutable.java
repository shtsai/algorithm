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
// Solution 2: improved version of solution 1
// if we don't need to keep the original array
class NumArray {
    private int[] preSum;
    
    public NumArray(int[] nums) {
        this.preSum = nums;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] += preSum[i-1];
        }
    }
    
    public int sumRange(int i, int j) {
               // [0, j] - [0, i]    +   [i]     = [i, j]
        return i == 0 ? preSum[j] : preSum[j] - preSum[i-1];
    }
}

// Solution 1: preSum approach
class NumArray {
    private int[] number;
    private int[] preSum;
    
    public NumArray(int[] nums) {
        this.number = nums;
        if (nums.length == 0) return;
        
        // preSum[i] = the sum of all numbers from index 0 to index i
        this.preSum = new int[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i];
        }
    }
    
    public int sumRange(int i, int j) {
               // [0, j] - [0, i]    +   [i]     = [i, j]
        return preSum[j] - preSum[i] + number[i];
    }
}

