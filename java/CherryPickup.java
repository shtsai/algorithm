/*
	In a N x N grid representing a field of cherries, each cell is one of three possible integers.

	0 means the cell is empty, so you can pass through;
	1 means the cell contains a cherry, that you can pick up and pass through;
	-1 means the cell contains a thorn that blocks your way.
	Your task is to collect maximum number of cherries possible by following the rules below:

	Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
	After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
	When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
	If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
	Example 1:
	Input: grid =
	[[0, 1, -1],
	 [1, 0, -1],
	 [1, 1,  1]]
	Output: 5
	Explanation: 
	The player started at (0, 0) and went down, down, right right to reach (2, 2).
	4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
	Then, the player went left, up, up, left to return home, picking up one more cherry.
	The total number of cherries picked up is 5, and this is the maximum possible.
	Note:

	grid is an N by N 2D array, with 1 <= N <= 50.
	Each grid[i][j] is an integer in the set {-1, 0, 1}.
	It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 */

// UNFINISHED, INCORRECT
class Solution {
    public int cherryPickup(int[][] grid) {
        int[][] memo1 = findMax(grid);
        int max = Math.max(memo1[memo1.length-1][memo1[0].length-1], 0);
        int[][] memo2 = findMax(grid);
        max += Math.max(memo2[memo2.length-1][memo2[0].length-1], 0);
        return max;
    }
    
    public int[][] findMax(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        memo[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] != -1 && grid[i-1][0] != -1) {
                memo[i][0] = grid[i][0] + memo[i-1][0];
            } else {
                memo[i][0] = -1;
            }
        }
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] != -1 && grid[0][i-1] != -1) {
                memo[0][i] = grid[0][i] + memo[0][i-1];
            } else {
                memo[0][i] = -1;
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == -1 || (memo[i-1][j] == -1 && memo[i][j-1] == -1)) {
                    memo[i][j] = -1;
                    continue;
                }
                int prevs = Math.max(memo[i-1][j], memo[i][j-1]);
                memo[i][j] = prevs + grid[i][j];
            }
        }
        int s = grid.length-1, t = grid[0].length-1;
        int max = memo[s][t], k = max;
        while (k > 0) {         // update board
            if (grid[s][t] == 1) {
                grid[s][t] = 0;
                k--;
            }
            if (s - 1 >= 0 && memo[s-1][t] == k) {
                s--;
            } else if (t - 1 >= 0 && memo[s][t-1] == k) {
                t--;
            }
        }

        return memo;
    }
}