/*
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once.
 */

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
