/*
 * Given a 2D matrix matrix, find the sum of the elements inside 
 * the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Note:
 * 1. You may assume that the matrix does not change.
 * 2. There are many calls to sumRegion function.
 * 3. You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

// Solution 2: Dynamic Programming
// Use dynamic programming to calculate results for all subproblems
// so look up later only take O(1) time
// Time: O(1)
// Space: O(mn)
// 09/08/2018

class NumMatrix {
    int[][] rangeSum;
    
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        rangeSum = new int[matrix.length + 1][matrix[0].length + 1];
        
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                rangeSum[i][j] = matrix[i - 1][j - 1] + rangeSum[i - 1][j] + rangeSum[i][j - 1] - rangeSum[i - 1][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return rangeSum[row2 + 1][col2 + 1] - rangeSum[row1][col2 + 1] - rangeSum[row2 + 1][col1] + rangeSum[row1][col1];
    }
}


// Solution 1: naive solution
// Do not store any additional information
// calculate range sum on the fly
// Time: O(mn)
// Space: O(1) - only store reference
// 09/08/2018

public class NumMatrix {
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
}
