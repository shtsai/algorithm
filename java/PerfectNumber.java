/*
 * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
 *
 * Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
 */

public class Solution {
    public boolean checkPerfectNumber(int num) {
        // base case
        if (num <= 4) return false;
        
        int result = 0;
        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                result += (i + num/i);  // add both numbers to the result
            }
        }
        result++;  // add 1 because 1 is a positive divisor of all numbers
        return result == num;
    }
}