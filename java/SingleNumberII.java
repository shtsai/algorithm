/*
 * Given an array of integers, every element appears three times except for one. Find that single one.
 */

// count the number of 1s in each bit position
public class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int result = 0;
        
        for (int i = 0; i < 32; i++) {
            int mask = (1 << i);   // create bit mask for each bit
            int count = 0;
            
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & mask) == mask) {   // if the bit position is 1
                    count++;                      // increment the count
                }
            }
            if (count % 3 != 0) {  // remainder is not 0, thus single number has this bit
                result |= mask;    // mark this bit position in the result
            }
        }
        return result;
    }
}