/*
	Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

	Example 1:

	Input: [5,7]
	Output: 4
	Example 2:

	Input: [0,1]
	Output: 0
 */

// Solution 2: Bit manipulation
// For example, for number 26 to 30
// Their binary form are:
// 	   11010
//     11011
//     11100　　
//     11101　　
//     11110
// Because we are trying to find bitwise AND, so if any bit there are at least one 0 and one 1, it always 0. 
// In this case, it is 11000.
// So we are go to cut all these bit that they are different. 
// In this case we cut the right 3 bit.	
//
// Time: O(log(max(m, n)))
// Space: O(1)
// 09/03/2018

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n) {
            m = m >> 1;
            n = n >> 1;
            i++;
        }
        return m << i;
    }
}

// Solution 1: Brute force
// Time: O(m - n) - Time limit exceeded
// Space: O(1)
// 09/03/2018

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int res = -1;
        for (int i = m; i <= n; i++) {
            res &= i;
        }
        return res;
    }
}
