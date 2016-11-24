/*
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 *
 * Note that 1 is typically treated as an ugly number.
 */

public class Solution {
    public boolean isUgly(int num) {
        int[] primes = {2,3,5};   // store all primes
        for (int i : primes) {      
            while (num%i==0 && num > 0) {  // keep dividing num until it is not divisable by the primes
                num /= i;                  // also need to check num > 0
            }
        }

        return num == 1;  // eventually, a ugly number will become 1
    }
}