/**
 * Write an algorithm to determine if a number is "happy".
 *
 * A happy number is a number defined by the following process: 
 * Starting with any positive integer, replace the number by the sum of the squares of its digits, 
 * and repeat the process until the number equals 1 (where it will stay), 
 * or it loops endlessly in a cycle which does not include 1. 
 * Those numbers for which this process ends in 1 are happy numbers.
 *
 */


public class Solution {
    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        
        if (n == 4) {
            return false;
        }
        
        int[] digits = new int[10];
        int length = 0;
        
        while (n != 0) {
            int last = n % 10;
            digits[length] = last;
            length++;
            n = n / 10;
        }
        
        int newN = 0;
        for (int i = 0; i<length; i++) {
            newN += digits[i] * digits[i];
        }
        
        return isHappy(newN);
    }
}