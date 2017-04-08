/*
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
 *
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 *
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */

public class Solution {
    public int integerBreak(int n) {
        // base cases
        if (n == 2) return 1;
        if (n == 3) return 2;
        
        int result = 1;
        while (n > 4) {     // try to break n into as many as '3' as possible
            result *= 3;
            n -= 3;
        }
        result *= n;
        
        return result;
    }
}