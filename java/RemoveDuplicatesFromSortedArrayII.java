/*
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 *
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * Your function should return length = 5, 
 * with the first five elements of nums being 1, 1, 2, 2 and 3. 
 * It doesn't matter what you leave beyond the new length.
 */

// Solution 1: Two pointers
// Use a pointer to point at the new starting index,
// use the other to scan through the array.
// Only add numbers in the result that haven't been included twice
// Time: O(n)
// Space: O(1)
// Reference: https://discuss.leetcode.com/topic/17180/3-6-easy-lines-c-java-python-ruby
// 10/01/2017
public class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;   // i is the index of a duplicate
        for (int num : nums) {
            if (i < 2 || num > nums[i-2]) {  // if num > nums[i-2], nums can be put there to replace the duplicate
                nums[i] = num;
                i++;    
            }
        }
        return i;
    }
}