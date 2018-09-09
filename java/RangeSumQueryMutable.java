/*
	Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

	The update(i, val) function modifies nums by updating the element at index i to val.

	Example:
	Given nums = [1, 3, 5]
	sumRange(0, 2) -> 9
	update(1, 2)
	sumRange(0, 2) -> 8
	
	Note:
	The array is only modifiable by the update function.
	You may assume the number of calls to update and sumRange function is distributed evenly.
 */

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

// Solution 2: Binary index tree
// reference: https://leetcode.com/problems/range-sum-query-mutable/discuss/75753/Java-using-Binary-Indexed-Tree-with-clear-explanation
// Good when frequent updates
// Time: initialize - O(nlogn)
//       update - O(logn)
//       sum - O(n)
// Space: O(n)
// 09/09/2018
class NumArray {
    int[] tree;
    int[] nums;
    int len;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.len = nums.length;
        this.tree = new int[len + 1];
        for (int i = 0; i < nums.length; i++) {
            updateTree(i, nums[i]);
        }
    }
    
    private void updateTree(int i, int val) {
        i++;    // binary index tree needs to add 1
        while (i <= len) {
            tree[i] += val;
            // find next index
            // 1. find 2's complement (reverse all bits and plus 1)
            // 2. AND two numbers to find right most 1 bit
            // 3. add it to original index
            i += (i & -i);      
        }
    }
    
    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        updateTree(i, diff);
    }
    
    private int getSum(int i) {
        i++;     // binary index tree needs to add 1
        int res = 0;
        while (i > 0) {
            res += tree[i];
            // find its parent index
            // 1. find 2's complement (reverse all bits and plus 1)
            // 2. AND two numbers to find right most 1 bit
            // 3. subtract it to original index
            i -= (i & -i);  
        }
        return res;
    }
    
    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }
}

// Solution 1: Naive solution
// Time: initialize - O(n)
//       update - O(1)
//       sumRange - O(n)
// Space: O(n)
// 09/09/2018

class NumArray {
    int[] data;
    public NumArray(int[] nums) {
        data = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
    }
    
    public void update(int i, int val) {
        data[i] = val;
    }
    
    public int sumRange(int i, int j) {
        int res = 0;
        for (int k = i; k <= j; k++) {
            res += data[k];
        }
        return res;
    }
}

