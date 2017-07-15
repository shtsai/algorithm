/*
 * Let's play the minesweeper game (Wikipedia, online game)!
 * 
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
 * 
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:
 * 
 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 */

// DFS
public class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        // click poisition is a mine
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        // click position is empty
        reveal(board, click);
        return board;
    }
    
    public void reveal(char[][] board, int[] click) {
        // invalid index
        if (click[0] < 0 || click[0] >= board.length || click[1] < 0 || click[1] >= board[0].length) return;
        
        int count = 0;
        for (int i = click[0] - 1; i <= click[0] + 1; i++) {        // check adjacent row
            for (int j = click[1] - 1; j <= click[1] + 1; j++) {    // check adjacent col
                if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || (i == click[0] && j == click[1])) {
                    continue;
                }
                if (board[i][j] == 'M') {   // count # of mines
                    count++;
                }
            }
        }
        if (count != 0) {   // fill the block with the count, and stop searching
            board[click[0]][click[1]] = Character.forDigit(count, 10);
        } else {
            board[click[0]][click[1]] = 'B';
            for (int i = click[0] - 1; i <= click[0] + 1; i++) {        // check adjacent row
                for (int j = click[1] - 1; j <= click[1] + 1; j++) {    // check adjacent col
                    // add several conditions to avoid repeated searching
                    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || (i == click[0] && j == click[1]) || (board[i][j] != 'M' && board[i][j] != 'E')) continue;
                    else reveal(board, new int[]{i, j});
                }
            }
        }
        return;
    }
}