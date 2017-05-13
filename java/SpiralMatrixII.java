/*
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * For example,
 * Given n = 3,
 *
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */

public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        // use four indice to mark the starting and ending point
        int row_start = 0, row_end = n - 1;
        int col_start = 0, col_end = n - 1;
        int num = 1;
        
        // fill in the matrix in spiral order
        while (row_start <= row_end && col_start <= col_end) {
            for (int i = col_start; i <= col_end; i++) {
                matrix[row_start][i] = num++;
            }
            row_start++;
            
            for (int i = row_start; i <= row_end; i++) {
                matrix[i][col_end] = num++;
            }
            col_end--;
            
            for (int i = col_end; i >= col_start; i--) {
                matrix[row_end][i] = num++;
            }
            row_end--;
            
            for (int i = row_end; i >= row_start; i--) {
                matrix[i][col_start] = num++;
            }
            col_start++;
        }
        
        return matrix;
    }
}