/*
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * Input:
 * 
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 * 
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */

// Solution 1:
// Dynamic Programming
// build a 2-D array, vertical axis indicates start position
// horizontal axis indicates end position
//
// ex.  "bbbab"
//         b b b a b
//         -----------
//     b | 1 2 3 3 4
//     b |   1 2 2 3
//     b |     1 1 3
//     a |       1 1
//     b |         1
//
// dp[start][end]: the longest palindrome subsequence you can get from substring(start, end+1)
     
class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int start = len-1; start >= 0; start--) {    // start from the last char
            dp[start][start] = 1;   // every char itself is a palindrome 
            for (int end = start+1; end < len; end++) {   // iterate to the end
                if (s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = dp[start+1][end-1] + 2;   // add these two chars to palindrome
                } else { // two chars don't match, see previous results
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
            }
        }
        return dp[0][len-1];  // start from 0 to the end (len-1)
    }
}