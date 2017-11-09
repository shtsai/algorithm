/*
	Longest Increasing Path in a Matrix
	We can connect all eight neighbors as long as the difference between two values is greater than 3.
			^	^	^
			 \	|  /
		   <--	n  -->
		     /  |  \
		    v   v   v
    Return the length of the longest increasing path.
    
    -- Lucid OA
 */

// Brute force solution: DFS
// Do DFS on every cell, create seperate visited[][] for each DFS.
// b/c we don't know where the starting point of this longest increasing path will be.
// Time: O(m^2 * n^2) - mn times DFS, each DFS is O(mn)
// Space: O(mn)
// 11/08/2017

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;

class NonDecreasing {

	/**
	 * Take a rectangular grid of numbers and find the length
	 * of the longest sub-sequence.
	 * @return the length as an integer.
	 */
	public static int longestSequence(int[][] grid) {
		// TODO: implement this function
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean[][] visited = new boolean[rows][cols];
                max = Math.max(max, dfs(grid, i, j, visited)); 
            }
        }
		return max;
	}
    
    private static int dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (visited[i][j]) return 0;
        visited[i][j] = true;  // mark as visited
        int res = 1;
        for (int di = -1; di <= 1; di++) {     // check all eight direction
            for (int dj = -1; dj <= 1; dj++) {
                if (di == 0 && dj == 0) continue;   // skip the (i, j)
                if (i+di >= 0 && i+di < grid.length && j+dj >= 0 && j+dj < grid[0].length) { // valid index
                    if (!visited[i+di][j+dj] && Math.abs(grid[i][j] - grid[i+di][j+dj]) > 3) {
                        res = Math.max(res, 1 + dfs(grid, i+di, j+dj, visited));
                    }
                }
            }
        }
        return res;
    }

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int numRows = 0;
		int numCols = 0;
		String[] firstLine = reader.readLine().split("\\s+");
		numRows = Integer.parseInt(firstLine[0]);
		numCols = Integer.parseInt(firstLine[1]);

		int[][] grid = new int[numRows][numCols];

		for (int row = 0; row < numRows; row++) {
		    String[] inputRow = reader.readLine().split("\\s+");

		    for (int col = 0; col < numCols; col++) {
		        grid[row][col] = Integer.parseInt(inputRow[col]);
		    }
		}
		int length = longestSequence(grid);
		System.out.println(length);
	}
}
