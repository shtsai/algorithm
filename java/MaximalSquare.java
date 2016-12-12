/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 */

// Dynamic programming
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix==null || matrix.length==0) return 0;
        
        // create an array to memoize
        int[][] result = new int[matrix.length+1][matrix[0].length+1];
        int max = 0;
        // be careful about the indices
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i-1][j-1] == '1') {
                    // add 1 because the corresponding cell is '1'
                    result[i][j] = Math.min(result[i-1][j], Math.min(result[i-1][j-1],result[i][j-1])) + 1;
                    max = Math.max(max, result[i][j]);
                }
            }
        }
        
        return max*max;
    }
}