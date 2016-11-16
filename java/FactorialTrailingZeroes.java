/*
 * Given an integer n, return the number of trailing zeroes in n!.
 */

// every 0 is formed by 2 * 5
// so basically we need to check how many 5s there are
public class Solution {
    public int trailingZeroes(int n) {
        int result = 0;
        while (n >= 5) {
            n /= 5;
            result += n;
        }
        return result;
    }
}