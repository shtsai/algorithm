/*
 * Given an integer, write a function to determine if it is a power of three.
 */

// repeatedly divide n by 3 and see if the remainder is 0
public class Solution {
    public boolean isPowerOfThree(int n) {
        while (n % 3 == 0 && n > 0) {
            n = n / 3;
        }
        if (n == 1) return true;
        else return false;
    }
}