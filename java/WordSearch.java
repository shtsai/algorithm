/*
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, 
 * where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once.
 */

// Solution 1: Recursive + visited 
// Time: O(mn * 4 ^ s)
// Space: O(mn)
// 09/24/2018

class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return false;
        } else if (word.charAt(index) != board[i][j]) {
            return false;
        }
        visited[i][j] = true;
        for (int[] d : dirs) {
            if (search(board, word, index + 1, i + d[0], j + d[1], visited)) {
                visited[i][j] = false;
                return true;
            }
        }
        visited[i][j] = false;
        return false;
    }
}