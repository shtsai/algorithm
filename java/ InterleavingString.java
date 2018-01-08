/*
 *  Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 *
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */

// Solution 4: Bottom-up DP
// Put s1 and s2 as the vertical and horizontal axis.
// At each state DP[i][j], there are three possibilities
//      1. s1[i] == s3[i+j], result depends on dp[i-1][j]
//      2. s2[j] == s3[i+j], result depends on dp[i][j-1]
//      3. Otherwise, false
//
// reference: https://discuss.leetcode.com/topic/7728/dp-solution-in-java
// Time: O(mn)
// Space: O(mn) - dp arrays

// version 2:
// 01/07/2018
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] memo = new boolean[s1.length()+1][s2.length()+1];
        
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    memo[i][j] = true;
                } else if (i == 0) {
                    memo[i][j] = s2.charAt(j-1) == s3.charAt(j-1) && memo[i][j-1];
                } else if (j == 0) {
                    memo[i][j] = s1.charAt(i-1) == s3.charAt(i-1) && memo[i-1][j];
                } else {
                    memo[i][j] = (s1.charAt(i-1) == s3.charAt(i+j-1) && memo[i-1][j]) 
                              || (s2.charAt(j-1) == s3.charAt(i+j-1) && memo[i][j-1]);
                }
            }
        }     
        return memo[s1.length()][s2.length()];
    }
}

// version 1:
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;

        // dp[i][j] == true when substring s1[0:i] and s2[0:j] interleave s3[0:i+j-1]
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        dp[0][0] = true;    // base case
        for (int i = 1; i < s1.length()+1; i++) {   // initialize the first row and first col
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        }
        for (int j = 1; j < s2.length()+1; j++) {
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }
        for (int i = 1; i < s1.length()+1; i++) {
            for (int j = 1; j < s2.length()+1; j++) {
                // dp[i-1][j] == true --> first i-1 of s1 and j of s2 matches s3[0, i+j-2]
                // we need to see if the next char in s1 matches next char in s3
                // Same applies for the second check
                dp[i][j] = (dp[i-1][j]&&s1.charAt(i-1)==s3.charAt(i+j-1)) ||
                    (dp[i][j-1]&&s2.charAt(j-1)==s3.charAt(i+j-1));
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

// Solution 3: DFS + memoization, top-down DP
// Use a DP array to store intermediate results.
// 0 for unseen problems, 1 for true, -1 for false.
// Time: O(mn) - m * n possible calls
// Space: O(mn) - memo arrays
// 01/07/18
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        if (s1.equals("")) return s2.equals(s3);        // need these two cases to handle empty string
        if (s2.equals("")) return s1.equals(s3);
        int[][] memo = new int[s1.length()+1][s2.length()+1];
        return search(s1, s2, s3, 0, 0, "", memo);
    }
    
    public boolean search(String s1, String s2, String s3, int i, int j, String current, int[][] memo) {
        if (i == s1.length() && j == s2.length() && current.equals(s3)) {
            memo[i][j] = 1;
            return true;
        }
        if (memo[i][j] != 0) {
            return memo[i][j] == 1 ? true : false;
        }
        
        if (i < s1.length() && s1.charAt(i) == s3.charAt(i+j) && search(s1, s2, s3, i+1, j, current + s1.charAt(i), memo)) {
            memo[i][j] = 1;
            return true;
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(i+j) && search(s1, s2, s3, i, j+1, current + s2.charAt(j), memo)) {
            memo[i][j] = 1;
            return true;
        }
        memo[i][j] = -1;
        return false;
    }
}


// Solution 2: Improved search
// Improvement upon brute force approach.
// Search only when the current character matches the character in target string.
// Significantly reduces the number of branches.
// Time: O(2^(m+n)) - time limit exceeded
// Space: O(m + n)
// 01/07/2018

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        if (s1.equals("")) return s2.equals(s3);        // need these two cases to handle empty string
        if (s2.equals("")) return s1.equals(s3);
        return search(s1, s2, s3, 0, 0, "");
    }
    
    public boolean search(String s1, String s2, String s3, int i, int j, String current) {
        if (i == s1.length() && j == s2.length() && current.equals(s3)) return true;
        
        if (i < s1.length() && s1.charAt(i) == s3.charAt(i+j) && search(s1, s2, s3, i+1, j, current + s1.charAt(i))) {
            return true;
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(i+j) && search(s1, s2, s3, i, j+1, current + s2.charAt(j))) {
            return true;
        }
        return false;
    }
}

// Solution 1: Brute force
// Generate all permutations and see if there is a match with s3
// Time: O(2^(m+n)) - time limit exceeded
// Space: O(m+n) - n level call stack
// 01/06/2018
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        return search(s1, s2, s3, 0, 0, "");
    }
    
    public boolean search(String s1, String s2, String s3, int i, int j, String current) {
        if (i == s1.length() && j == s2.length() && current.equals(s3)) return true;
        
        if (i < s1.length() && search(s1, s2, s3, i+1, j, current + s1.charAt(i))) {
            return true;
        }
        if (j < s2.length() && search(s1, s2, s3, i, j+1, current + s2.charAt(j))) {
            return true;
        }
        return false;
    }
}