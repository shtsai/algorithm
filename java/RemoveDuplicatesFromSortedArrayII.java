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