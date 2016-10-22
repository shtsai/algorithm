/*
 * Given a 2d grid map of '1's (land) and '0's (water), 
 * count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 */

public class Solution {
    public int numIslands(char[][] grid) {

        // think of this grid as a graph
        // each 1 represents a node and is connected with other 1s that are next to it
        // use DFS to traverse all the connected nodes

        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int count = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {  // found an island
                    grid[i][j] = '0';     // remove island so that we won't visit it again later
                    findIsland(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void findIsland(char[][] grid, int i, int j) {
        // check up, down, left, and right
        // first check if the indices are valid

        if (i - 1 > -1) {
            if (grid[i-1][j] == '1') {
                grid[i-1][j] = '0';
                findIsland(grid, i-1, j);
            }
        }
        if (i + 1 < grid.length) {
            if (grid[i+1][j] == '1') {
                grid[i+1][j] = '0';
                findIsland(grid, i+1, j);    
            }
        }
        if (j - 1 > -1) {
            if (grid[i][j-1] == '1') {
                grid[i][j-1] = '0';
                findIsland(grid, i, j - 1);
            }
        }
        if (j + 1 < grid[0].length) {
            if (grid[i][j+1] == '1'){
                grid[i][j+1] = '0';
                findIsland(grid, i, j+1);
            }
        }
    }
}