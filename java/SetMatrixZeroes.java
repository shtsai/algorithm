/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */

public class Solution {
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        // use two bool arrays to keep track of the zero rows and cols
        boolean[] zeroRow = new boolean [row];
        boolean[] zeroCol = new boolean [col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // if an element (i,j) is 0, then row i is a zero row and col j is a zero col
                // mark them as true in the bool array
                if (matrix[i][j] == 0) {
                    zeroRow[i] = true;
                    zeroCol[j] = true;
                }
            }
        }
        

        // if row i is a zero row, change the whole row into 0s
        for (int i = 0; i < row; i++) {
            if (zeroRow[i]) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // if col j is a zero col, change the whole col into 0s
        for (int j = 0; j < col; j++) {
            if (zeroCol[j]) {
                for (int i = 0; i < row; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}