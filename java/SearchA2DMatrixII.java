/*
 * Write an efficient algorithm that searches for a value in an m x n matrix. 
 *
 * This matrix has the following properties:
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 */

public class Solution {
    // iterative approach
    // O(m+n)
    public boolean searchMatrix(int[][] matrix, int target) {
        // start from upper right corner
        int i = 0;
        int j = matrix[0].length - 1;
        
        while (i < matrix.length && j >= 0) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                i++;  // because j is the largest on this row, target cannot be on this row
            } else {
                j--;  // i is the smallest on this column, target not on this column
            }
        }
        
        return false;
    }

    /*
    // recursive solution one
    // repeated calculation, time limit exceeded
    public boolean searchMatrix(int[][] matrix, int target) {
        return helper(matrix, target, 0, 0); // start from upper left corner
    }    
    public boolean helper(int[][] matrix, int target, int i, int j) {
        if (i >= matrix.length || j >= matrix[0].length) {
            return false;
        }
        if (matrix[i][j] == target) {
            return true;
        }
        if (matrix[i][j] > target) {
            return false;
        }
        return helper(matrix, target, i+1, j) || helper(matrix,target, i, j+1);
    }
    */
}