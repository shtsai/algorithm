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

// Solution 3:
// observation: when two islands merge, the shared edge disappears.
// so perimeter = 4 * island - 2 * neighbor
// just need to count the number of islands and neighbors
// 
// Time: O(mn)
// Space: O(1)
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

// Solution 2: Union Find
// Use the union find approach to find all islands.
// When we find a new island, increment perimeter by 4.
// However, it there is a connected neighbors on the top or the left,
// we need to subtract perimeter by 2.
//
// Time: O(mn)
// Space: O(mn) - root array
// 11/08/2017

class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        int[] root = new int[rows * cols];
        Arrays.fill(root, -1);
        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {  // find a new island
                    root[i * cols + j] = i * cols + j;
                    res += 4;
                    if (i > 0 && grid[i-1][j] == 1) {
                        res -= 2;
                        union(root, i * cols + j, (i-1) * cols + j);
                    }
                    if (j > 0 && grid[i][j-1] == 1) {
                        res -= 2;
                        union(root, i * cols + j, i * cols + j - 1);
                    }
                }
            }
        }
        return res;
    }
    private void union(int[] root, int a, int b) {
        int ra = find(root, a);
        int rb = find(root, b);
        if (ra != rb) {
            root[ra] = rb;
        }
    }
    private int find(int[] root, int a) {
        while (a != root[a]) {
            a = root[a];
        }
        return a;
    }
}

// Solution 1: DFS search
