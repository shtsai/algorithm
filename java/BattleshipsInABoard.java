/*
	Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:

	You receive a valid board, made of only battleships or empty slots.
	Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
	At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
	Example:
	X..X
	...X
	...X
	In the above board there are 2 battleships.
	Invalid Example:
	...X
	XXXX
	...X
	This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
	Follow up:
	Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 */

// Solution 2: Count first cell only
// We only count the first cells of battleships.
// The first cells is a 'X' and they don't have other 'X' to their top and left.
//
// Time: O(n^2)
// Space: O(1)
// 12/02/2017
class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int countBattleships(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    if (i - 1 >= 0 && board[i-1][j] == 'X') {
                        continue;
                    }
                    if (j - 1 >= 0 && board[i][j-1] == 'X') {
                        continue;
                    }
                    count++;
                }
            }
        }
        return count;
    }
}

// Solution 1: DFS
// Similar to number of islands.
// Use a 2D boolean array to keep track of whether we have visited a cell yet.
// 
// Time: O(n^2)
// Space: O(n^2)
// 12/01/2017

class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int countBattleships(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X' && !visited[i][j]) {
                    count++;
                    search(board, visited, i, j);
                }
            }
        }
        return count;
    }
    
    private void search(char[][] board, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'X' || visited[i][j]) return;
        visited[i][j] = true;
        for (int[] dir : dirs) {
            search(board, visited, i + dir[0], j + dir[1]);
        }
    }
}