/**
 * Given an integer, (signed 32 bits), write a function to check whether it is a power of 4.
 */

public class Solution {
    public boolean isPowerOfFour(int num) {

    	// we can solve this using bit manipulation
    	// 1. num can only have one "1" bit, so num&(num-1) must equals to 0
    	// 2. num can only have one "1" bit in the odd position, so num&0x55555555 must not equal to 0
    	// 3. num should be greater than 0

        return (num&(num-1)) == 0 && (num & 0x55555555) != 0 && num > 0;
    }
}