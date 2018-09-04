/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 */

// Solution 1: Dynamic programming
// DP[i][j] = size of maximum square with bottom-right corner at i, j
// DP[i][j] = 
//   - if matrix[i][j] == '1':
//        Math.min(DP[i-1][j-1], DP[i-1][j], DP[i][j-1]) + 1 
//   - otherwise, 0
//
// Time: O(mn)
// Space: O(mn)
// 09/04/2018

public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix==null || matrix.length==0) return 0;
        
        // create an dp array 
        int[][] result = new int[matrix.length+1][matrix[0].length+1];
        int max = 0;
        // be careful with the indices
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i-1][j-1] == '1') {
                    result[i][j] = Math.min(result[i-1][j], Math.min(result[i-1][j-1],result[i][j-1])) + 1;
                    max = Math.max(max, result[i][j]);
                }
            }
        }
        return max * max;
    }
}