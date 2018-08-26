/*
	On a N * N grid, we place some 1 * 1 * 1 cubes.
	Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
	Return the total surface area of the resulting shapes.

	Example 1:
	Input: [[2]]
	Output: 10

	Example 2:
	Input: [[1,2],[3,4]]
	Output: 34

	Example 3:
	Input: [[1,0],[0,2]]
	Output: 16

	Example 4:
	Input: [[1,1,1],[1,0,1],[1,1,1]]
	Output: 32

	Example 5:
	Input: [[2,2,2],[2,1,2],[2,2,2]]
	Output: 46
	 

	Note:
	1 <= N <= 50
	0 <= grid[i][j] <= 50
 */

// Solution 1: 
// Count surface area of each cell and overlap area.
// Then subtract the two.
//
// Time: O(n ^ 2)
// Space: O(1)
// 08/25/2018

class Solution {
    final int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int surfaceArea(int[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    sum += (6 * grid[i][j] - 2 * (grid[i][j] - 1));                    
                }
            }
        }
        int overlap = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                for (int[] dir : dirs) {
                    int newI = i + dir[0];
                    int newJ = j + dir[1];
                    if (newI < 0 || newI >= grid.length || newJ < 0 || newJ >= grid[0].length) {
                        continue;
                    }
                    overlap += Math.min(grid[i][j], grid[newI][newJ]);
                }
            }
        }
        return sum - overlap;
    }
}