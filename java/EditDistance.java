/*
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 *
 */

public class Solution {
    public int minDistance(String word1, String word2) {
        int s = word1.length();
        int t = word2.length();
        
        if (word1 == null || s == 0) return t;
        if (word2 == null || t == 0) return s;
        
        int[][] dp = new int[s+1][t+1];
        
        // initialize the dp table
        for (int i = 0; i <= s; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= t; j++) {
            dp[0][j] = j;
        }
        
        // if char i and char j are the same, then the result depends on dp[i-1][j-1]
        // otherwise, find the min among three operations and add 1
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < t; j++) {
                
                if (word1.charAt(i) != word2.charAt(j)) {
                    // dp[i][j]    -> replace
                    // dp[i+1][j]  -> delete
                    // dp[i][j+1]  -> insert
                    dp[i+1][j+1]= Math.min(dp[i][j], Math.min(dp[i+1][j], dp[i][j+1])) + 1;
                } else {
                    dp[i+1][j+1] = dp[i][j];
                }
                
            }
        }
        
        return dp[s][t];
    }
}