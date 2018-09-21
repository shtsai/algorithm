/*
    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

    Note: You can only move either down or right at any point in time.

    Example:
    Input:
    [
      [1,3,1],
      [1,5,1],
      [4,2,1]
    ]
    Output: 7
    Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

// Solution 3: DP + print path
// Time: O(mn)
// Space: O(mn)
// 09/21/2018
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        int[][] prev = new int[rows][cols];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            prev[i][0] = (i - 1) * cols;
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
            prev[0][j] = j - 1;
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = grid[i][j];
                if (dp[i][j - 1] < dp[i - 1][j]) {
                    dp[i][j] += dp[i][j - 1];
                    prev[i][j] = i * cols + (j - 1);
                } else {
                    dp[i][j] += dp[i - 1][j];
                    prev[i][j] = (i - 1) * cols + j;
                }
            }
        }
        
        Stack<Integer> stack = new Stack<>();
        int r = rows - 1;
        int c = cols - 1;
        for (int i = 0; i < rows + cols - 1; i++) {
            stack.push(grid[r][c]);
            int p = prev[r][c];
            r = p / cols;
            c = p % cols;
        }
        
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        
        return dp[rows - 1][cols - 1];
    }
}

// Solution 2: DP + new DP array
// Time: O(mn)
// Space: O(mn)
// 09/21/2018
class Solution {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[rows - 1][cols - 1];
    }
}

// Solution 1: DP inplace
// Time: O(mn)
// Space: O(1)
// 09/21/2018
public class Solution {
    public int minPathSum(int[][] grid) {
        // initialize the edges of the grid
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] += grid[0][i-1];
        }
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] += grid[i-1][0];
        }
        
        // the choice of grid[i][j] depends on grid[i-1][j] and grid[i][j-1]
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        
        return grid[grid.length-1][grid[0].length-1];
    }
}