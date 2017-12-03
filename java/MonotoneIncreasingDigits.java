/*
	Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

	(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

	Example 1:
	Input: N = 10
	Output: 9
	Example 2:
	Input: N = 1234
	Output: 1234
	Example 3:
	Input: N = 332
	Output: 299
	Note: N is an integer in the range [0, 10^9].
 */

// Solution 1:
// First check if the number is monotonically increasing.
// If not, repeatedly "wipe out" its less signficant numbers 
// till it becomes monotonically increasing.
// For example, 
// 543: wipe out 3 and minus 1 => 540 - 1 = 539 (FALSE)
// 540: wipe out 40 and minus 1 => 500 - 1 = 499 (TRUE)
//
// Time: O(n^2) - n is the length of the number
//				- each check is O(n) time, worst case check n times
// Space: O(1)
// 12/02/2017
class Solution {
    public int monotoneIncreasingDigits(int N) {
        if (isIncreasing(N)) return N;
        int n = N, length = 0;
        while (n > 0) {
            n /= 10;
            length++;
        }
        n = N;
        for (int i = 1; i < length; i++) {
            int base = (int) Math.pow(10, i);
            n = n / base * base;
            if (isIncreasing(n-1)) {
                return n - 1;
            }
        }
        return 0;
    }
    
    // helper function that checks if a number is monotonically increasing
    public boolean isIncreasing(int N) {
        while (N > 0) {
            int last = N % 10;
            N /= 10;
            if (last < (N % 10)) {
                return false;
            }
        }
        return true;
    }
}