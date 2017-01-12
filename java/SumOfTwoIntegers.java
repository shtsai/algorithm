/*
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 *
 * Example:
 * Given a = 1 and b = 2, return 3.
 */

// using bit manipulation
public class Solution {
    public int getSum(int a, int b) {
        if (a == 0) return b;
        
        while (b != 0) {
            int carry = a & b;  // the bits that a and b both have (causing a carry)
            a = a ^ b;          // the different bits between a and b 
            b = carry << 1;     // carry should left shift by 1 
        }
        return a;
    }
}