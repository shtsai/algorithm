/*
 * 
 * Given an array of n integers where n > 1, nums, 
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Solve it without division and in O(n).
 *
 * For example, given [1,2,3,4], return [24,12,8,6].
 *
 */

/* Solution 1:
    Trick: contruct the array in the following way
    Example of a four-element array: 
    
    {              1,         a[0],    a[0]*a[1],    a[0]*a[1]*a[2],  }
    { a[1]*a[2]*a[3],    a[2]*a[3],         a[3],                 1,  }

    reference:
    http://stackoverflow.com/questions/2680548/given-an-array-of-numbers-return-array-of-products-of-all-other-numbers-no-div

    Time: O(n)
    Space: O(n)
 */

// Solution 2: 
// Use only one array and two passes, constant space
// Time: O(n)
// Space: O(1)

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        result[0] = 1;                              // first run
        for (int i = 1; i < nums.length; i++) {     // build from left to right
            result[i] = result[i-1] * nums[i-1];
        }
        
        int right = 1;                              // second run
        for (int i = nums.length-1; i >= 0; i--) {  // build from right to left
            result[i] = result[i] * right;
            right = right * nums[i];
        }
        
        return result;
    }
}

// Solution 1:
// use two arrays and three passes
// Time: O(n)
// Space: O(n)
// 11/16/2017
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] * nums[i-1];
        }
        right[nums.length-1] = 1;
        for (int i = nums.length-2; i >= 0; i--) {
            right[i] = right[i+1] * nums[i+1];
        }
        for (int i = 0; i < nums.length; i++) {
            right[i] *= left[i];
        }
        return right;
    }
}
