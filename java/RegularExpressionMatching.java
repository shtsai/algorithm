/*
    Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.
    The matching should cover the entire input string (not partial).

    Note:
    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.
    
    Example 1:
    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    
    Example 2:
    Input:
    s = "aa"
    p = "a*"
    Output: true
    Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
    
    Example 3:
    Input:
    s = "ab"
    p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".
    
    Example 4:
    Input:
    s = "aab"
    p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
    
    Example 5:
    Input:
    s = "mississippi"
    p = "mis*is*p*."
    Output: false
 */

// Solution 3: Iterative DP
// Time: O(sp)
// Space: O(sp)
// 08/31/2018
    
class Solution {
    public boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                                       (pattern.charAt(j) == text.charAt(i) ||
                                        pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}

// Solution 2: Recursion + Memoization
// Time: O(sp)
// Space: O(sp)
// 08/31/2018

class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return helper(s, 0, p, 0, memo);
    }
    
    private boolean helper(String s, int sIndex, String p, int pIndex, Boolean[][] memo) {
        if (memo[sIndex][pIndex] != null) {
            return memo[sIndex][pIndex];
        } 
        
        if (pIndex == p.length()) {
            memo[sIndex][pIndex] = sIndex == s.length();
        } else {
            boolean firstMatch = sIndex != s.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.');
        
            if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
                memo[sIndex][pIndex] = (firstMatch && helper(s, sIndex + 1, p, pIndex, memo)) || helper(s, sIndex, p, pIndex + 2, memo);
            } else {
                memo[sIndex][pIndex] = firstMatch && helper(s, sIndex + 1, p, pIndex + 1, memo);
            }
        }
        return memo[sIndex][pIndex];
    }
}

// Solution 1: Recursion
// At each recursive call, we check:
//     - if we have used all the pattern
//     - if the first character is matched
//     - if there is a '*' at pIndex + 1
// 
// Time: O(3 ^ (s + p))
// 08/31/2018

class Solution {
    public boolean isMatch(String s, String p) {
        return helper(s, 0, p, 0);
    }
    
    private boolean helper(String s, int sIndex, String p, int pIndex) {
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }
        
        boolean firstMatch = sIndex != s.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.');
        
        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            return (firstMatch && helper(s, sIndex + 1, p, pIndex)) || helper(s, sIndex, p, pIndex + 2);
        } else {
            return firstMatch && helper(s, sIndex + 1, p, pIndex + 1);
        }
               
    }
}