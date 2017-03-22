/*
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 */

//Dynamic Programming
public class Solution {
    public int climbStairs(int n) {
        int first = 1;
        int second = 2;
        if (n == 0 || n == 1) return 1;
        
        for (int i = n - 2; i > 0; i--) {
            int steps = first + second;
            first = second;
            second = steps;
        }
        
        return second;
    }
}