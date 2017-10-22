/*
	Your are given an array of positive integers nums.

	Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

	Example 1:
	Input: nums = [10, 5, 2, 6], k = 100
	Output: 8
	Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
	Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
	Note:

	0 < nums.length <= 50000.
	0 < nums[i] < 1000.
	0 <= k < 10^6.
 */

// Solution 1: two pointers
// Use two pointers to find a window with product less than k.
// When product exceeds k, add (right-left) to counter.
// (right - left) is the number of continuous subarrays that includes nums[left].
// Divide product by nums[left].
// Then move left pointer to the right by one unit.
// If left is greater than right, move right to where left it.
// Time: O(n) - two pointers, two pass
// Space: O(1)
// 10/21/2017

class Solution { 
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = 0;
        int product = 1, res = 0;
        while (right < nums.length || left < nums.length) {
            while (right < nums.length && product * nums[right] < k) {
                product *= nums[right];
                right++;
            }
            
            res += right-left;
            
            product /= nums[left];
            left++;
            if (right < left) right = left;
        }
        return res;
    }
}