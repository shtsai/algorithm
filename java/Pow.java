/*
 * Implement pow(x, n).
 */

// Solution 3: Recursion
// Time: O(logn)
// Space: O(logn)
// 01/09/2018
class Solution {
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;
        if (n == 1) return x;
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = N * -1;
        }
        double singular = N % 2 == 1 ? x : 1;
        N /= 2;
        return myPow(x * x, (int) N) * singular;
    }
}

// Solution 2:
// Recursion: recursively divide problems into smaller subproblems
// Time: O(lgn)
// Space: O(lgn) - call stack
public class Solution {
    public double myPow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;
        if (n == 1) return x;
        long N = n;
        if (N < 0) {
            N *= -1;
            x = 1 / x;
        }

        double half = myPow(x, (int) (N / 2));      // recursive call
        
        return N % 2 == 0 ? half * half : half * half * x;
    }
}

// Solution 1: Brute force
// Standard implementation
// Time: O(n) - Time Limit Exceeded
// Space: O(1)
public class Solution {
    public double myPow(double x, int n) {
        int sign = 1;       // need to handle negative n
        if (n < 0) {
            n *= -1;
            sign = -1;
        }
        
        double res = 1;
        for (;n > 0; n--) {
            res *= x;
        }
        
        return sign == 1 ? res : (1 / res);
    }
}
