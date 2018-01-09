/*
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).

 * The matching should cover the entire input string (not partial).

 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)

 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 */

// Solution 2: Button-up DP
// Put two string s and p on the two sides of a matrix.
// DP[i][j] represent whether str1[0:i] matches str2[0:j]
// Compare each pair of characters str1[i] and str2[j].
//      1. Two chars are the same OR '?'
//              result depends on DP[i-1][j-1]
//      2. '*'
//              - treat it as an empty string => DP[i][j-1]
//              - treat it as the matching char => DP[i-1][j-1]
//              - treat it as an extending char => DP[i-1][j]
//      3. Mismatch, return false
//
// Time: O(mn)
// Space: O(mn)
// 01/09/2018

class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            dp[0][i] = dp[0][i-1] && p.charAt(i-1) == '*';
        }
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j-1] || dp[i-1][j] || dp[i][j-1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        
        return dp[s.length()][p.length()];
    }
}

// Solution 1: 
// Scan through two strings using two pointers, compare char by char.
// When see a '*', recording its position j and the matching index i.
// When find mismatch, return to that star index and retry.
// For each retry, increment i by 1.
// Finally, check whether we have use all chars in p. If so, return true.
// Time: O(mn)
// Space: O(1)
// 01/09/2018
public class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0;
        int j = 0;
        int starIndex = -1;
        int iIndex = -1;
 
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {  // find a match, continue
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {  // find a star, record the indices
                starIndex = j;      
                iIndex = i;
                j++;
            } else if (starIndex != -1) {   // mismatch occurs but star exists, increment i see if it matches starIndex+1
                j = starIndex + 1;
                i = iIndex+1;
                iIndex++;
            } else {    // mismatch 
                return false;
            }
        }
 
        while (j < p.length() && p.charAt(j) == '*') {  // get rid of the '*'s at the end
            ++j;
        }
 
        return j == p.length();     // after these operations, see if j is pointing at the end of string p
    }
}