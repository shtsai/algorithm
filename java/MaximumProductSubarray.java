/*
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */

// Solution 2:
// dynamic programming
public class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0], min = nums[0], result = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            int max2 = max; // a copy of max

            // update max and min
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(max2 * nums[i], min * nums[i]), nums[i]);
            if (max > result) result = max;
        }
        
        return result;
    }
}

// Solution 1:
// brute force
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 1) return nums[0];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int product = nums[i];
            if (product > max) max = product;
            for (int j = i + 1; j < nums.length; j++) {
                product *= nums[j];
                if (product > max) max = product;
            }
        }
        return max;
    }
}