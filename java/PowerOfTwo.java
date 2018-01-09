/**
 * Given an integer, write a function to determine if it is a power of two.
 */
   

// Solution 3: Recursion
// Time: O(logn)
// Space: O(log) - call stack
// 01/08/2018
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n == 1) return true;
        if (n == 0 || n % 2 != 0) return false;
        return isPowerOfTwo(n / 2);
    }
}


// Solution 2: Bit
// observation: in the binary representation, all numbers that are powers of two have only one "1"
// first check if n is postive
// second, if n only contains one "1" in its binary representation
// then n&(n-1) must be equal to 0
// Time: O(1)
// Space: O(1)
public class Solution {
    public boolean isPowerOfTwo(int n) {        
        return n>0 && (n&(n-1))==0;
    }
}


// Solution 1: Bit
// Observation: all power of two in binary form has one 1-bit and trailing 0s
// Time: O(1) - worst case check all 32 bits
// Space: O(1)
class Solution {
    public boolean isPowerOfTwo(int n) {
        while (n != 0) {
            // first remove all the trailing 0s
            int lastBit = n & 1;
            n = n >> 1;
            
            // when we find an "1", check if the value is 0 after we do a right shift
            // if yes, then this "1" is the only 1
            if (lastBit == 1) {
                if (n == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}