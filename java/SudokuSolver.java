/*
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * You may assume that there will be only one unique solution. 
 */

// Solution 1:
// brute force + backtracking
// for every empty cell, we try all possible numbers,
// and recursively solve
// Time: O(9^m) - m is the number of empty cells

class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }
    
    private boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {   // find an empty cell
                    for (char n = '1'; n <= '9'; n++) {  // try 1->9
                        if (isValid(board, i, j, n)) {
                            board[i][j] = n;
                            if (solve(board)) {   // recursively solve
                                return true;
                            } else {
                                board[i][j] = '.';  // no correct, try another
                            }
                        }
                    }
                    return false;   // tried all possible, cannot solve, false
                }
            }
        }
        return true;    // no empty cell, return true
    }
    
    // a helper function that finds whether n already exist in its
    // row, col, and square
    private boolean isValid(char[][] board, int row, int col, char n) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] != '.' && board[row][i] == n) return false;
            if (board[i][col] != '.' && board[i][col] == n) return false;
            // row/3*3 and col/3*3 gives you the upper left cell in its square
            if (board[row/3*3+i/3][col/3*3+i%3]!='.'&&board[row/3*3+i/3][col/3*3+i%3]==n) return false;
        }
        return true;
    }
}