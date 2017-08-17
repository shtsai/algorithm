/*
 * Given an integer matrix, find the length of the longest increasing path.
 * 
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * 
 * Example 1:
 * 
 * nums = [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 * 
 * Example 2:
 * 
 * nums = [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

// Dynamic programming
// recursively solve smaller subproblems, and store results in an memoized arrays
public class Solution {
    int max = 0;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int R = matrix.length, C = matrix[0].length, max = 1;
        int[][] dp = new int[R][C];     // initialize dp arrays (0: unvisited)
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (dp[i][j] == 0) visit(matrix, dp, i, j);
                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }
    
    private int visit(int[][] matrix, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) return dp[i][j];
        int R = matrix.length, C = matrix[0].length;
        
        if (i - 1 >= 0 && matrix[i-1][j] > matrix[i][j]) {
            dp[i][j] = Math.max(dp[i][j], 1 + visit(matrix, dp, i-1, j));
        }
        if (i + 1 < R && matrix[i+1][j] > matrix[i][j]) {
            dp[i][j] = Math.max(dp[i][j], 1 + visit(matrix, dp, i+1, j));
        }
        if (j - 1 >= 0 && matrix[i][j-1] > matrix[i][j]) {
            dp[i][j] = Math.max(dp[i][j], 1 + visit(matrix, dp, i, j-1));
        }
        if (j + 1 < C && matrix[i][j+1] > matrix[i][j]) {
            dp[i][j] = Math.max(dp[i][j], 1 + visit(matrix, dp, i, j+1));
        }
            
        if (dp[i][j] == 0) dp[i][j] = 1;   // mark as visited
        return dp[i][j];
    }
}