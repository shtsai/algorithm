/*
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 * 
 * Example:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output:  [1,2,4,7,5,3,6,8,9]
 */

public class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        
        int m = matrix.length, n = matrix[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0;
        boolean d = true;      // direction: true -> upward, false -> downward
        
        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            
            if (d) {        // change row and col according to the direction
                row--;      // upward diagonal
                col++;
            } else {
                row++;      // downward diagonal
                col--;
            }
            
            // cross lower bound
            if (row >= m) { row--; col += 2; d = !d; }
            // cross right bound
            if (col >= n) { col--; row += 2; d = !d; }
            // cross upper bound
            if (row < 0) { row = 0; d = !d; }
            // cross left bound
            if (col < 0) { col = 0; d = !d; }
        }
        return result;
    }
}