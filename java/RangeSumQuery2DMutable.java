/*
	Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

	Example:
	Given matrix = [
	  [3, 0, 1, 4, 2],
	  [5, 6, 3, 2, 1],
	  [1, 2, 0, 1, 5],
	  [4, 1, 0, 1, 7],
	  [1, 0, 3, 0, 5]
	]

	sumRegion(2, 1, 4, 3) -> 8
	update(3, 2, 2)
	sumRegion(2, 1, 4, 3) -> 10

	Note:
	The matrix is only modifiable by the update function.
	You may assume the number of calls to update and sumRegion function is distributed evenly.
	You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */

// Solution 1: 2D binary index tree
// Use two binary index trees
// Time: initialize - O(mn * logn * logm)
//       update - O(logn * logm)
//       sumRegion - O(logn * logm)
// 09/09/2018

class NumMatrix {
    int[][] tree;
    int[][] nums;
    int m, n;
    
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        this.nums = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.tree = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                updateTree(i, j, nums[i][j]);
            }
        }
    }
    
    private void updateTree(int row, int col, int val) {
        for (int i = row + 1; i <= m; i += (i & -i)) {
            for (int j = col + 1; j <= n; j += (j & -j)) {
                tree[i][j] += val;
            }
        }
    }
    
    public void update(int row, int col, int val) {
        int diff = val - nums[row][col];
        nums[row][col] = val;
        updateTree(row, col, diff);
    }
    
    private int getSum(int row, int col) {
        int res = 0;
        for (int i = row + 1; i > 0; i -= (i & -i)) {
            for (int j = col + 1; j > 0; j -= (j & -j)) {
                res += tree[i][j];
            }
        }
        return res;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2, col2) + getSum(row1 - 1, col1 - 1) - getSum(row1 - 1, col2) - getSum(row2, col1 - 1);
    }
}

