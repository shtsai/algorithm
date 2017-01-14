/*
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, calculate the Hamming distance.
 */

public class Solution {
    public int hammingDistance(int x, int y) {
        int result = 0;
        for (int i = 0; i < 32; i++) { // compare every bit
            if ((x & 1) != (y & 1)) {
                result++;
            }
            x = x >> 1;
            y = y >> 1;
        }
        return result;
    }
}

/*
// use XOR, then count number of 1-bit
public class Solution {
    public int hammingDistance(int x, int y) {
        int result = x ^ y;
        int count = 0;
        while (result != 0) {
            count += (result & 1);
            result = result >>> 1;
        }
        return count;
    }
}
*/