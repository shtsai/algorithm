/*
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */

// first find all 'O' that are not surrounded by 'X', these 'O's must be at the edge of the board
// then we do DFS on these 'O' to find all other 'O's that are connected to it, and mark them as '*'
// finally, all 'O's that are unchanged must be surrounded by 'X's

public class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        
        // search all four edges
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
                findO(board, 0, i);
            }
            if (board[row-1][i] == 'O'){
                findO(board, row-1, i);
            }
        }
        for (int j = 1; j < row; j++) {
            if (board[j][0] == 'O') {
                findO(board, j, 0);
            }
            if (board[j][col-1] == 'O') {
                findO(board, j, col-1);
            }
        }
        
        // recover the board, turn all 'O' to 'X', and all '*' to 'O'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    public void findO(char[][] board, int i, int j) {
        // mark the position as '*'
        board[i][j] = '*';
        
        // continue searching all its neighbors, DFS
        if (j - 1 > 0 && board[i][j-1] == 'O') {
            findO(board, i, j-1);
        }
        if (j + 1 < board[0].length && board[i][j+1] == 'O') {
            findO(board, i, j+1);
        }
        if (i - 1 > 0 && board[i-1][j] == 'O') {
            findO(board, i-1, j);
        }
        if (i + 1 < board.length && board[i+1][j] == 'O'){ 
            findO(board, i+1, j);
        }
    }
}