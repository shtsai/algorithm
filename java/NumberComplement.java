/*
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 *
 * Note:
 * The given integer is guaranteed to fit within the range of a 32-bit signed integer.
 * You could assume no leading zero bit in the integer’s binary representation.
 *
 */

public class Solution {
    public int findComplement(int num) {
        int tmp = num;
        int count = 0;
        while (tmp > 0) { // count how many bits the current number has
            count++;
            tmp = tmp >>> 1;
        }
        return (~num) & (-1 >>> (32-count)); // flip the bits and apply the mask
    }
}