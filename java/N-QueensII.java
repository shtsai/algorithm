/*
 * Follow up for N-Queens problem.
 * 
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 */

// Solution 2: 
// No need to actually build the board, just check if col, diagonals conflicts.
// Time: O(n ^ n)
// Space: O(n)
// 09/09/2018
class Solution {
    int count;
    public int totalNQueens(int n) {
        count = 0;
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[n * 2];
        boolean[] diag2 = new boolean[n * 2];
        search(0, n, cols, diag1, diag2);
        return count;
    }
    
    private void search(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            count++;
            return;
        }
        for (int col = 0; col < n; col++) {
            int d1 = col - row + n;
            int d2 = col + row;
            
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            search(row + 1, n, cols, diag1, diag2);
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}

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