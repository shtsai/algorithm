/*
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 * 
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * 
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 */

// dynamic programming 
// use 2D array to store results of smaller subproblems

// example:
// S = "rabbbit", T = "rabbit"
//
//          r a b b b i t        <- T
//        |----------------
//     r  | 1 1 1 1 1 1 1
//     a  |   1 1 1 1 1 1
//     b  |     1 2 3 3 3
//     b  |       1 3 3 3
//     i  |         0 3 3
//     t  |           0 3
//

public class Solution {
    public int numDistinct(String s, String t) {
        if (s == null || t == null || s.length() == 0) return 0;
        if (t.length() == 0) return 1;
        
        int[][] dp = new int[t.length()][s.length()];
        
        dp[0][0] = s.charAt(0) == t.charAt(0) ? 1 : 0;   // initialize first row
        for (int j = 1; j < s.length(); j++) {      
            if (s.charAt(j) == t.charAt(0)) dp[0][j] = 1 + dp[0][j-1];
            else dp[0][j] = dp[0][j-1];
        }
        
        for (int i = 1; i < t.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (t.charAt(i) == s.charAt(j)) dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                else dp[i][j] = dp[i][j-1];
            }
        }
        
        return dp[t.length()-1][s.length()-1];
    }
}