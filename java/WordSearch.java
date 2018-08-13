/*
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once.
 */

// Solution 2: Recursive + visited 
// Time: O(mn * 4 ^ s)
// Space: O(mn)
class Solution {
    final int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    if (search(board, word, 0, i, j, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, String word, int index, int r, int c, boolean[][] visited) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || visited[r][c] || board[r][c] != word.charAt(index)) {
            return false;
        }
        if (index == word.length() - 1) {
            return true;
        }
        visited[r][c] = true;
        for (int[] dir : dirs) {
            if (search(board, word, index + 1, r + dir[0], c + dir[1], visited)) {
                return true;
            }
        }
        visited[r][c] = false;   // reset visited so that we can use this char later in another path
        return false;
    }
}

// Solution 1: Iterative + modify input
// Time: O(mn * 4 ^ s)
// Space: O(1)
public class Solution {
    public boolean exist(char[][] board, String word) {
	    int row = board.length;
	    int col = board[0].length;
	    char firstChar = word.charAt(0);
	    boolean result = false;

	    for (int i = 0; i < row; i++) {
	        for (int j = 0; j < col; j++) {
		        if (board[i][j] == firstChar) { // find first char
		            board[i][j] = '~';           // remove it from the board, indicating that we selected it
		            result |= findNextChar(board, i, j, 1, word);
		            board[i][j] = firstChar;    // restore the char
		        }
		        if (result) { return result; }
	        }
	    }
	    return result;
    }
    
    public boolean findNextChar(char[][] board, int i, int j, int charIndex, String word) {
       	if (charIndex == word.length()){
	    return true;
	}
	
	int row = board.length;
	int col = board[0].length;
	char target = word.charAt(charIndex);
	boolean result = false;

	if (i - 1 > -1 && result != true) {   // if left exists and haven't find word
	    if (board[i-1][j] == target) {
		board[i-1][j] = '~';
		result |= findNextChar(board, i-1, j, charIndex+1, word);
		board[i-1][j] = target;
	    }
	}
	if (i + 1 < row && result != true) { // if right exists and haven't find word
	    if (board[i+1][j] == target) {
		board[i+1][j] = '~';
		result |= findNextChar(board, i+1, j, charIndex+1, word);
		board[i+1][j] = target;
	    }
	}
	if (j - 1 > -1 && result != true) { // if up exists and haven't find word
	    if (board[i][j-1] == target) {
		board[i][j-1] = '~';
		result |= findNextChar(board, i, j-1, charIndex+1, word);
		board[i][j-1] = target;
	    }
	}
	if (j + 1 < col && result != true) { // if down exists and haven't find word
	    if (board[i][j+1] == target) {
		board[i][j+1] = '~';
		result |= findNextChar(board, i, j+1, charIndex+1, word);
		board[i][j+1] = target;	   
	    }
	}
	return result;
    }
}
