/*
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * For example,
 * Given the following matrix:
 *
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 */

// traverse the matrix in spiral order and store the elements on the way
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;
        
        int row_start = 0, row_end = matrix.length-1, col_start = 0, col_end = matrix[0].length-1;
        
        while (row_start <= row_end && col_start <= col_end) {
            for (int i = col_start; i <= col_end; i++) {
                result.add(matrix[row_start][i]);
            }
            row_start++;
            
            for (int i = row_start; i <= row_end; i++) {
                result.add(matrix[i][col_end]);
            }
            col_end--;
            
            if (row_start <= row_end) {  // need this check to prevent duplicates
                for (int i = col_end; i >= col_start; i--) {
                    result.add(matrix[row_end][i]);
                }
                row_end--;
            }
            
            if (col_start <= col_end) {  // need this check to prevent duplicates
                for (int i = row_end; i >= row_start; i--) {
                    result.add(matrix[i][col_start]);
                }
                col_start++;
            }
        }
        
        return result;
    }
}