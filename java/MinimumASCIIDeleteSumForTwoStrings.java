/*
	Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.

	Example 1:
	Input: s1 = "sea", s2 = "eat"
	Output: 231
	Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
	Deleting "t" from "eat" adds 116 to the sum.
	At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
	Example 2:
	Input: s1 = "delete", s2 = "leet"
	Output: 403
	Explanation: Deleting "dee" from "delete" to turn the string into "let",
	adds 100[d]+101[e]+101[e] to the sum.  Deleting "e" from "leet" adds 101[e] to the sum.
	At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
	If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.
	Note:

	0 < s1.length, s2.length <= 1000.
	All elements of each string will have an ASCII value in [97, 122].
 */

// Solution 1: Dynamic programming
// Use the same approach as Edit distance problem.
// Create a 2D dp array, put s1 on vertical axis, and put s2 on horizontal axis.
// Each cell contains the number of min ASCII sum to perform transform.
// Recursion relation:
//		if c1[i] == c2[j]: then we look at the result from previous substring.
//		if c1[i] != c2[j]: then we look up and left to find a minimun value.
//
// Time: O(mn) - len(s1) = m, len(s2) = n
// Space: O(mn) - 2D array
// 10/21/2017

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        
        int[][] dp = new int[c1.length+1][c2.length+1];
        for (int i = 1; i <= c1.length; i++) {
            dp[i][0] = dp[i-1][0] + c1[i-1];
        }
        for (int j = 1; j <= c2.length; j++) {
            dp[0][j] = dp[0][j-1] + c2[j-1];
        }
        
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                if (c1[i-1] == c2[j-1]) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + c1[i-1], dp[i][j-1]+c2[j-1]);
                }
            }
        }
        
        return dp[c1.length][c2.length];
    }
}