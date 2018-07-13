/*
    Given a string, your task is to count how many palindromic substrings in this string.

    The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

    Example 1:
    Input: "abc"
    Output: 3
    Explanation: Three palindromic strings: "a", "b", "c".
    Example 2:
    Input: "aaa"
    Output: 6
    Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
    Note:
    The input string length won't exceed 1000.
 */

// Solution 3: Expand around center
// Try all different centers.
// With each center, count odd and even palindromes.
//
// Time: O(n^2)
// Space: O(1)
// 07/12/2018
    
class Solution {
    public int countSubstrings(String s) {
        int len = s.length(), count = 0;
        for (int left = 0; left < len; left++) {
            int l1 = left, r1 = left;
            while (l1 >= 0 && r1 < len && s.charAt(l1) == s.charAt(r1)) {
                l1--;
                r1++;
                count++;
            }
            int l2 = left, r2 = left + 1;
            while (l2 >= 0 && r2 < len && s.charAt(l2) == s.charAt(r2)) {
                l2--;
                r2++;
                count++;
            }
        }
        return count;
    }
}

// Solution 2: Dynamic Programming
// Define dp[i, j] = whether substring(i, j) is a palindrome
// Recurrence relation:
//     1. if (i == j) -> true (same char)
//     2. if (i + 1 == j && s[i] == s[j]) -> true (two chars palindrome)
//     3. if s[i] == s[j], check dp[i+1, j-1]
//
// Time: O(n^2)
// Space: O(n^2)
// 07/12/2018

class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; i + j < s.length(); j++) {
                int r = j, c = i + j;
                if (r == c) {
                    dp[r][c] = true;
                    count++;
                } else if (r + 1 == c) {
                    if (s.charAt(r) == s.charAt(c)) {
                        dp[r][c] = true;
                        count++;
                    }
                } else if (s.charAt(r) == s.charAt(c)) {
                    dp[r][c] = dp[r + 1][c - 1];
                    if (dp[r][c]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}


// Solution 1: Naive solution
// Time: O(n^3)
// Space: O(1)
// 07/12/2018
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindromic(s, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean isPalindromic(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}