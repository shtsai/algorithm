/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has 
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, 
 * so the function should return 3.
 */

// use for loop
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += 1 & (n >>> i);   // unsigned right shift
        }
        return result;
    }
}

/*  // use while loop
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
        	// n & 1 masks the last bit
            count += (n & 1);

            // >>> unsign right shift
            n = n >>> 1;
        }
        return count;
    }
}
*/ 