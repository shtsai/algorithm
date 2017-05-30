/*
 * Follow up for N-Queens problem.
 * 
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 */

public class Solution {
    public int res = 0;
    
    public int totalNQueens(int n) {
        int[] config = new int[n];
        fill_row(config, 0);
        return res;
    }
    
    public void fill_row(int[] config, int row) {
        if (row == config.length) {
            res++; 
            return;
        }
        for (int i = 0; i < config.length; i++) {
            if (isValid(config, row, i)) {
                config[row] = i;
                fill_row(config, row+1);
                config[row] = 0;
            }
        }
    }
    
    public boolean isValid(int[] config, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (config[i] == col || Math.abs(i-row) == Math.abs(config[i]-col)) {
                return false;
            }
        }
        return true;
    }
}