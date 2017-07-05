/*
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * 
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 * 
 * Example 1:
 * 
 * Input:
 * 3
 * 
 * Output:
 * 3
 * Example 2:
 * 
 * Input:
 * 11
 * 
 * Output:
 * 0
 * 
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 * 
 */

public class Solution {
    public int findNthDigit(int n) {
        int start = 1;      // starting number
        int len = 1;        // length of digits
        long count = 9;      // count of numbers with the above len [need to use type long]
        
        while (n > len * count) {   // n is outside the current range
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }
        
        int num = start + (n-1) / len;   // find the right number
        char result = Integer.toString(num).charAt((n-1) % len);
        return Character.getNumericValue(result);
    }
}