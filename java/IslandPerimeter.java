/*
 * You are given a map in form of a two-dimensional integer grid where 1 represents land 
 * and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). 
 * The grid is completely surrounded by water, and there is exactly one island 
 * (i.e., one or more connected land cells). 
 * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
 * One cell is a square with side length 1. 
 *
 * The grid is rectangular, width and height don't exceed 100. 
 * Determine the perimeter of the island.
 */

// observation: when two islands merge, the shared edge disappears.
// so perimeter = 4 * island - 2 * neighbor
// just need to count the number of islands and neighbors
public class Solution {
    public int islandPerimeter(int[][] grid) {
        int island = 0;     // stores the number of islands
        int neighbor = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    island++;
                    if (i+1 < grid.length && grid[i+1][j] == 1) neighbor++;
                    if (j+1 < grid[0].length && grid[i][j+1] == 1) neighbor++;
                }
            }
        }
        return 4 * island - 2 * neighbor;
    }
}