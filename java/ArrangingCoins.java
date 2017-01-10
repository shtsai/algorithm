/*
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 */

// brute force solution, count level by level
public class Solution {
    public int arrangeCoins(int n) {
        int result = 0;
        int level = 1;
        n = n - level;  // first level
        while (n >= 0) {
            result++;
            level++;
            n -= level;
        }
        return result;
    }
}