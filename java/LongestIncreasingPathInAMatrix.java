/*
    Given an integer matrix, find the length of the longest increasing path.

    From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

    Example 1:

    Input: nums = 
    [
      [9,9,4],
      [6,6,8],
      [2,1,1]
    ] 
    Output: 4 
    Explanation: The longest increasing path is [1, 2, 6, 9].
    Example 2:

    Input: nums = 
    [
      [3,4,5],
      [3,2,6],
      [2,2,1]
    ] 
    Output: 4 
    Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */

// Solution 1: Dynamic Programming
// Do DFS on every cell and maintain a global maximum
// recursively solve smaller subproblems, and cache results in an memoized arrays
// Time: O(mn)
// Space: O(mn)
// 09/23/2018
class Solution {
    int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (dp[i][j] == 0) {
                    search(matrix, dp, i, j);
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
    
    private int search(int[][] matrix, int[][] dp, int i, int j) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int val = 0;
        for (int[] d : dirs) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni < 0 || ni >= matrix.length || nj < 0 || nj >= matrix[0].length || matrix[ni][nj] <= matrix[i][j]) {
                continue;
            }
            val = Math.max(val, search(matrix, dp, ni, nj));
        }
        dp[i][j] = val + 1;
        return dp[i][j];
    }
}