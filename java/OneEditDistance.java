/*
    Given two strings S and T, determine if they are both one edit distance apart.
 */

// Solution 2:
// Make sure s is shorter than t
// Use a pointer i to scan through two strings concurrently
// When see a mismatch, consider the following cases:
//      a. When i is at the end of s, all previous chars match, except the last char in t
//      b. When sLen == tLen, we skip the mismatched char, and continue
//      c. When sLen < tLen, we skip one char in t because it is one char longer
// Finally, we see if i reaches the end of s
//
// Time: O(n) - one pass
// Space: O(1)
// 09/30/2017
    
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen > tLen) return (isOneEditDistance(t, s));
        if (tLen - sLen > 1) return false;
        
        int offset = tLen - sLen;   // 0 | 1
        int i = 0;
        while (i < sLen && s.charAt(i) == t.charAt(i)) i++;
        if (i == sLen) return offset == 1;  // previous all match, t has one more char
        if (offset == 0) i++;   // skip the mismatch char
        while (i < sLen && s.charAt(i) == t.charAt(i+offset)) i++;
        return i == sLen;   // see if we reach the end

    }
}

// Solution 1: standard edit distance approach
// Dynamic programming, use 2D array to store intermediate results
// Time: O(mn) - time limit exceeded
// Space: O(mn)
// 09/30/2017

class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (Math.abs(sLen - tLen) > 1) return false;
        
        int[][] dp = new int[sLen+1][tLen+1];
        dp[0][0] = 0;
        for (int i = 1; i <= sLen; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= tLen; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int minMove = Math.min(Math.min(dp[i-1][j], dp[i][j-1]),dp[i-1][j-1]);
                    dp[i][j] = minMove + 1;
                }
            }
        }
        return dp[sLen][tLen] == 1;
    }
}