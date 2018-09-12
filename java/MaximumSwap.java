/*
	Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

	Example 1:
	Input: 2736
	Output: 7236
	Explanation: Swap the number 2 and the number 7.
	
	Example 2:
	Input: 9973
	Output: 9973
	Explanation: No swap.
	Note:
	The given number is in the range [0, 108]
 */

// Solution 1: 
// Store the last occurrence of each digits.
// Then start from left, try swap with the largest digit possible.
// Time: O(n)
// Space: O(n)
// 09/12/2018

class Solution {
    public int maximumSwap(int num) {
        char[] n = Integer.toString(num).toCharArray();
        int[] digits = new int[10];
        
        for (int i = 0; i < n.length; i++) {
            digits[n[i] - '0'] = i;
        }
        
        for (int i = 0; i < n.length; i++) {
            for (int j = 9; j > n[i] - '0'; j--) {
                if (digits[j] > i) {
                    char temp = n[i];
                    n[i] = n[digits[j]];
                    n[digits[j]] = temp;
                    return Integer.parseInt(new String(n));
                }
            }
        }
        return num;
    }
}