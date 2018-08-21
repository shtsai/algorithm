/*
	Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...
	So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...
	Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

	Example 1:
	Input: 9
	Output: 10
	
	Hint: n will not exceed 9 x 10^8.
 */

// Solution 2: Math
// After removing all occurrence of 9s,
// the numbers become base-9.
// https://leetcode.com/problems/remove-9/solution/
// 
// Time: O(1)
// Space: O(1)
// 08/21/2018
class Solution {
    public int newInteger(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }
}

// Solution 1: Brute force
// Generate all n numbers.
// Check every digit to see if the number contains 9.
//
// Time: O(n * 10) - Time limit exceeded
// Space: O(1)
// 08/21/2018

class Solution {
    public int newInteger(int n) {
        int value = 0;
        while (n > 0) {
            value++;
            if (!contains9(value)) {
                n--;
            }
        }
        return value;
    }
    
    private boolean contains9(int value) {
        while (value > 0) {
            if (value % 10 == 9) {
                return true;
            } else {
                value /= 10;
            }
        }
        return false;
    }
}