/**
 * Given an integer, write a function to determine if it is a power of two.
 */
     
public class Solution {
    public boolean isPowerOfTwo(int n) {
        // observation:
        // in the binary representation, all numbers that are powers of two have only one "1"


        // solution two, one line
        // first check if n is postive
        // second, if n only contains one "1" in its binary representation
        // then n&(n-1) must be equal to 0
        return n>0 && (n&(n-1))==0;



        // solution one
        /*
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
        */
    }
}