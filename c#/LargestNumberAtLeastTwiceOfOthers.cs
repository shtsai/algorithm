/*
    In a given integer array nums, there is always exactly one largest element.

    Find whether the largest element in the array is at least twice as much as every other number in the array.

    If it is, return the index of the largest element, otherwise return -1.

    Example 1:

    Input: nums = [3, 6, 1, 0]
    Output: 1
    Explanation: 6 is the largest integer, and for every other number in the array x,
    6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
     

    Example 2:

    Input: nums = [1, 2, 3, 4]
    Output: -1
    Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
     

    Note:

    nums will have a length in the range [1, 50].
    Every nums[i] will be an integer in the range [0, 99].
 */

// Solution 1: One pass
// Find max and second max, compare the two
// Time: O(n)
// Space: O(n)
// 02/20/2018
public class Solution {
    public int DominantIndex(int[] nums) {
        int max = Int32.MinValue;
        int maxIndex = -1;
        int secondMax = Int32.MinValue;
        int secondIndex = -1;
        
        for (int i = 0; i < nums.Length; i++) {
            if (maxIndex == -1 || nums[i] > max) {
                secondMax = max;
                secondIndex = maxIndex;
                max = nums[i];
                maxIndex = i;
            } else if (secondIndex == -1 || nums[i] > secondMax) {
                secondMax = nums[i];
                secondIndex = i;
            }
        }
        
        if (secondIndex == -1 || max >= secondMax * 2) {
            return maxIndex;
        } else {
            return -1;
        }
    }
}

// Solution 1: Two pass
// First pass finds max.
// Second pass checks if the max is at lease twice as much as every other numbers.
// 
// Time: O(n)
// Space: O(n)
// 02/20/2018

public class Solution {
    public int DominantIndex(int[] nums) {
        int max = Int32.MinValue;
        int maxIndex = -1;
        for (int i = 0; i < nums.Length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        
        for (int i = 0; i < nums.Length; i++) {
            if (i == maxIndex) {
                continue;
            }    
            if (nums[i] * 2 > max) {
                return -1;
            }
        }
        
        return maxIndex;
    }
}