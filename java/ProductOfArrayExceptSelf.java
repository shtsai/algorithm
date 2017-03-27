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


/*
    Trick: contruct the array in the following way
    Example of a four-element array: 
    
    {              1,         a[0],    a[0]*a[1],    a[0]*a[1]*a[2],  }
    { a[1]*a[2]*a[3],    a[2]*a[3],         a[3],                 1,  }

    reference:
    http://stackoverflow.com/questions/2680548/given-an-array-of-numbers-return-array-of-products-of-all-other-numbers-no-div
*/

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