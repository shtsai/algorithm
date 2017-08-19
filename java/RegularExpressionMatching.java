/*
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */

// dynamic programming 
class Solution {
    public boolean isMatch(String s, String p) {
        int lenS = s.length(), lenP = p.length();
        
        // dp table, additional row and col for empty char at beginning
        boolean[][] dp = new boolean[lenS+1][lenP+1];   
        dp[0][0] = true;
        for (int j = 1; j < lenP; j++) {    // initialize first row
            // '*' and the char on its left together can become empty string 
            if (p.charAt(j) == '*') dp[0][j+1] = dp[0][j-1];
        }
        for (int i = 1; i < lenS+1; i++) {  // initialize first col
            dp[i][0] = false;
        }
        
        for (int i = 0; i < lenS; i++) {
            for (int j = 0; j < lenP; j++) {
                char x = p.charAt(j);
                if (x == s.charAt(i) || x == '.') dp[i+1][j+1] = dp[i][j]; // match
                else if (x != '*') dp[i+1][j+1] = false;                // mismatch
                else {  // x == '*'
                    if (s.charAt(i) == p.charAt(j-1) || (p.charAt(j-1) == '.')) {
                        dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1];
                    }
                    else dp[i+1][j+1] = dp[i+1][j-1];
                }
            }
        }
        
        return dp[lenS][lenP];
    }
}