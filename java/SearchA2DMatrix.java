/*
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * For example,
 *
 * Consider the following matrix:
 *
 * [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * Given target = 3, return true.
 */

// Solution 2:
// treat the whole matrix as one sorted list
// use / and % to locate each cell
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        int mid;
        while (low <= high) {
            mid = (low + high)/2;
            if (matrix[mid/n][mid%n] == target) {   
                return true;
            } else if (matrix[mid/n][mid%n] > target) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        
        return false;
    }
}

// Solution 1:
// 2D binary search
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // target is out of range
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || target < matrix[0][0] || target > matrix[matrix.length-1][matrix[0].length-1]) return false;

        int row_low = 0, row_high = matrix.length-1, row = (row_low+row_high)/2;
        int col_low = 0, col_high = matrix[0].length-1, col = (col_low + col_high)/2;
        
        // binary search to find correct row
        while (row_low <= row_high) {
            row = (row_low+row_high)/2;
            if (target >= matrix[row][col_low] && target <= matrix[row][col_high]) {    
                break;
            } else if (target > matrix[row][col_high]) {
                row_low = row+1;
            } else {
                row_high = row-1;
            }
        }
        
        // binary search to find correct column
        while (col_low <= col_high) {
            col = (col_low + col_high)/2;
            if (target == matrix[row][col]) {
                return true;
            } else if (target > matrix[row][col]) {
                col_low = col+1;
            } else {
                col_high = col-1;
            }
        }
        
        return false;
    }
}