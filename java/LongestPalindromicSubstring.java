/*
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * 
 * Example:
 * 
 * Input: "babad"
 * 
 * Output: "bab"
 * 
 * Note: "aba" is also a valid answer.
 * Example:
 * 
 * Input: "cbbd"
 * 
 * Output: "bb"
 */

// Solution 2: Dynamic Programming
// dp[i][j] = whether substring (i, j) is palindrome
// Recurence relation:
//     dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]
// Base case:
//     s.charAt(i) == s.charAt(j) and j - i < 2  
//     e.g. "A", "AA"
//
// Time: O(n ^ 2)
// Space: O(n ^ 2)
// 09/11/2018
class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                // j - i < 2 handles base case such as "a" and "aa"
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }
}

// Solution 1, version 2:
// use helper function to find Palindrome, cleaner 
// Time: O(n ^ 2)
// Space: O(1)
class Solution {
    private int max, maxLeft;
    
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        max = 0;
        maxLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            findPalindrome(s, i, i);        // one char as center
            findPalindrome(s, i, i+1);      // two chars as center
        }
        return s.substring(maxLeft, maxLeft+max);
    }
    
    private void findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left)==s.charAt(right)) {
            left--;
            right++;
        }
        if (max < right-left-1) {
            max = right-left-1;
            maxLeft = left+1;
        }
    }
}

// Solution 1:
// try centering at every position, use one char or two chars as center
// Time: O(n ^ 2)
// Space: O(1)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        int max = 0, maxLeft = 0;
        for (int i = 0; i < s.length(); i++) {
            int oneLeft = i, oneRight = i;
            int twoLeft = i, twoRight = i + 1;
            while (oneLeft >= 0 && oneRight < s.length() && s.charAt(oneLeft)==s.charAt(oneRight)) {
                oneLeft--;
                oneRight++;
            }
            if (max < oneRight-oneLeft - 1) {
                max = oneRight-oneLeft - 1;
                maxLeft = oneLeft + 1;
                
            }
            while (twoLeft >= 0 && twoRight < s.length() && s.charAt(twoLeft)==s.charAt(twoRight)) {
                twoLeft--;
                twoRight++;
            }
            if (max < twoRight-twoLeft - 1) {
                max = twoRight-twoLeft - 1;
                maxLeft = twoLeft + 1;
            }
        }
        return s.substring(maxLeft, maxLeft + max);
    }
}
