/*
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, calculate the Hamming distance.
 */

// Solution 2: Compare bit by bits
// version 2:
// Time: O(1) - at most 32 bits, O(31) = O(1)
// Space: O(1)
// 11/16/2017
class Solution {
    public int hammingDistance(int x, int y) {
        int count = 0;
        while (!(x == 0 && y == 0)) {
            count += (x & 1) ^ (y & 1);
            x = x >> 1;
            y = y >> 1;
        }
        return count;
    }
}

// version 1:
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

// Solution 1:
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
