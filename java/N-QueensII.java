/*
 * Follow up for N-Queens problem.
 * 
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 */

// Solution 1: DFS, backtracking
// Time: O(n^n) - n choices at n levels
// Space: O(n) - stack

// version 2:
// 01/31/2018
class Solution {
    int count;
    public int totalNQueens(int n) {
        count = 0;
        solver(new ArrayList<>(), 0, n);
        return count;
    }
    
    public void solver(List<Integer> placement, int index, int n) {
        if (index == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(placement, index, i)) {
                placement.add(i);
                solver(placement, index + 1, n);
                placement.remove(placement.size() - 1);
            }
        }
    }
    
    public boolean isValid(List<Integer> placement, int row, int col) {
        for (int i = 0; i < placement.size(); i++) {
            int j = placement.get(i);
            if (j == col) {     // same column
                return false;
            }
            if (i + j == row + col) {  // diagonal
                return false;
            }
            if (i - j == row - col) {  // anti-diagonal
                return false;
            }
        }
        return true;
    }
}

// version 1:
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