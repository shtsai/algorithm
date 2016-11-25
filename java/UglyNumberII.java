/*
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 *
 * Note that 1 is typically treated as an ugly number.
 */

// Dynamic programming
public class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int two = 0, three = 0, five = 0;  // keep track of the current position of the three pointer
        
        for (int i = 1; i < n; i++) {
            // find the minimun of three candidate
            ugly[i] = Math.min(ugly[two]*2, Math.min(ugly[three]*3, ugly[five]*5));
            if (ugly[i] == ugly[two]*2) two++;      // if a candidate is selected, increment its pointer
            if (ugly[i] == ugly[three]*3) three++;  // note that multiple candidate can have the same value
            if (ugly[i] == ugly[five]*5) five++;    // need to increment them together if that is the case
        }
        
        return ugly[n-1];
    }
}