/*
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * For example, Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
 * It doesn't matter what you leave beyond the new length.
 */

public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int available = 1;  // the first number is always valid, so second number is a possible available position
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {  // find a different number, swap it to the available position
                nums[available] = nums[i];
                available++;    // next index becomes available position
            }
        }
        
        return available;   // all numbers to the left of avaiable are distinct
    }
}

/*
public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int distinct = 0;     // a pointer that keeps track of the distinct number
        for (int cur = 1; cur < nums.length; cur++) { 
            if (nums[cur] != nums[cur-1]) {  // skip the duplicates, and put next distinct number after the previous one
                distinct++;
                nums[distinct] = nums[cur];
            }
        }
        return distinct + 1; 
        
    }
}
*/