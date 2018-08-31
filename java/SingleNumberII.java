/*
 * Given an array of integers, every element appears three times except for one. Find that single one.
 */

// Solution 2: 
// Compute bit sums on each bit position.
// Mod every bit position by 3.
// The result is the new number.
// Time: O(n)
// Space: O(1)
// 08/31/2018
class Solution {
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int n : nums) {
            for (int i = 0; i < 32; i++) {
                bits[31 - i] += (n >> i) & 1;
            }
        }
        int res = 0;
        for (int b : bits) {
            res = res * 2 + b % 3;
        }
        return res;
    }
}

// Solution 1:
// Count the number of 1s in each bit position
// Time: O(n)
// Space: O(1)
// 08/31/2018
class Solution {
    public int singleNumber(int[] nums) {
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