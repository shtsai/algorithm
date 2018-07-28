/*
	Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

	Example:

	Input: 13
	Output: 6 
	Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */

// Solution 1: Native solution
// Try all n, count all 1s
// Time: O(n * 32) = O(n)
// Space: O(1)
// 07/28/2018

class Solution {
    public int countDigitOne(int n) {
        int res = 0;
        while (n >= 0) {
            res += countOne(n);
            n--;
        }
        return res;
    }
    
    private int countOne(int n) {
        int res = 0;
        while (n > 0) {
            if (n % 10 == 1) {
                res++;
            }
            n /= 10;   
        }
        return res;
    }
}