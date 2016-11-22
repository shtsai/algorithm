/*
 * Given a 2D matrix matrix, find the sum of the elements inside 
 * the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Note:
 * 1. You may assume that the matrix does not change.
 * 2. There are many calls to sumRegion function.
 * 3. You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
// use dynamic programming to calculate results for all subproblems
// so look up later only take O(1) time
public class NumMatrix {
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;   
        }
        // use dynamic programming
        for (int j = 1; j < matrix[0].length; j++) {  // calculate dp first row
            matrix[0][j] += matrix[0][j-1];
        }
        for (int i = 1; i < matrix.length; i++) {     // calculate dp first col
            matrix[i][0] += matrix[i-1][0];
        }
        
        for (int i = 1; i < matrix.length; i++) {     // calculate dp matrix
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = matrix[i][j] + matrix[i-1][j] + matrix[i][j-1] - matrix[i-1][j-1];
            }
        }
        this.matrix = matrix;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (col1 == 0 && row1 == 0) return matrix[row2][col2];                 // if start on [0][0]
        if (col1 == 0) return matrix[row2][col2] - matrix[row1-1][col2];       // if start on first col
        if (row1 == 0) return matrix[row2][col2] - matrix[row2][col1-1];       // if start on first row
        return matrix[row2][col2] - matrix[row1-1][col2] - matrix[row2][col1-1] + matrix[row1-1][col1-1];
    }
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);

/*  naive solution, do not store any additional information, calculate range sum on the fly, very slow
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
*/

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);