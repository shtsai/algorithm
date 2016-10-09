/*
 * Game of Life  
 * https://leetcode.com/problems/game-of-life/ 
 *
 * a better in-place solution: https://discuss.leetcode.com/topic/29054/easiest-java-solution-with-explanation/2 
 */

public class Solution {
    public void gameOfLife(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        int[][] copy = new int[row][col];
        
        // make a copy of the board
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                copy[i][j] = board[i][j];
            }
        }
        
        // iterate through the board, and count the number of live cells nearby
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++){
                int live = 0;
                
                // if the row above exists
                if (i - 1 > -1) {
                    if (j - 1 > -1) {                       // top left
                        live += copy[i-1][j-1];
                    }
                    live += copy[i-1][j];         // top middle
                    if (j + 1 < col) {
                        live += copy[i-1][j+1];    // top right
                    }
                }
                
                // middle row
                if (j - 1 > -1) {                          // middle left
                    live += copy[i][j-1];
                }
                if (j + 1 < col) {                        // middle right
                    live += copy[i][j+1];
                }
                
                // buttom row exists
                if (i + 1 < row) {
                    if (j - 1 > -1) {                      // bottom left
                        live += copy[i+1][j-1];
                    }
                    live += copy[i+1][j];        // bottom middle
                    if (j + 1 < col) {                    // bottom right
                        live += copy[i+1][j+1];
                    }
                }
                
                if (board[i][j] == 1) {  // current cell alive
                    if (live < 2) board[i][j] = 0;
                    else if (live == 2 || live == 3) board[i][j] = 1;
                    else board[i][j] = 0;
                } else {
                    if (live == 3) board[i][j] = 1;
                }
            }
        }
        
    }
}