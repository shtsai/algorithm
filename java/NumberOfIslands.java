/*
 * Given a 2d grid map of '1's (land) and '0's (water), 
 * count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 */

// Solution 3: Union Find
// Initially assign the parent of each node to be itself.
// Then we scan the grid. When we find a edge connecting 
// two nodes, we find their (root) parent nodes. If their
// root parent nodes are the same, they are in the same group.
// Otherwise, union them together.
// Lastly, check how many root nodes we have, which is the 
// number of islands in the grid.
// Time: O(mn)
// Space: O(mn)
// 11/03/2017

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int row = grid.length, col = grid[0].length;
        int[] parent = new int[row * col];
        for (int i = 0; i < row * col; i++) {
            parent[i] = i;   // initialze parent node to each node itself
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    if (i - 1 >= 0 && grid[i-1][j] == '1') {
                        union(parent, i * col + j, (i-1) * col + j);
                    }
                    if (j - 1 >= 0 && grid[i][j-1] == '1') {
                        union(parent, i * col + j, i * col + j - 1);
                    }
                }
            }
        }
        
        int res = 0;
        for (int i = 0; i < row * col; i++) {
            if (grid[i/col][i%col] == '1' && parent[i] == i) {
                res++;
            }
        }
        return res;
    }
    
    private void union(int[] parent, int i, int j) {
        int ip = find(parent, i);
        int jp = find(parent, j);
        if (ip != jp) {
            parent[ip] = jp;    // connect to disjoint component
        }
    }
    
    private int find(int[] parent, int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];  // path compression
            i = parent[i];
        }
        return i;
    }
}

// Solution 2: 
// If modification of the input array is not allow, we can use a 
// boolean array to indicate whether or not we have visited a cell.
// Time: O(mn)
// Space: O(mn)
// 10/13/2017

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res++;
                    search(grid, i, j, visited);
                }
            }
        }
        return res;
    }
    
    private void search(char[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if (grid[i][j] != '1' || visited[i][j]) return;
        
        visited[i][j] = true;
        search(grid, i-1, j, visited);
        search(grid, i+1, j, visited);
        search(grid, i, j-1, visited);
        search(grid, i, j+1, visited);
    }
}

// Solution 1: improved version 2
// Assuming we are allowed to modify the input array
// Time: O(mn) 
// Space: O(1)
// 10/13/2017
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    search(grid, i, j);
                }
            }
        }
        return res;
    }
    
    private void search(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
        if (grid[i][j] != '1') return;
        
        grid[i][j] = '0';
        search(grid, i-1, j);
        search(grid, i+1, j);
        search(grid, i, j-1);
        search(grid, i, j+1);
    }
}

// Solution 1: improved version
// much more concise
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int island = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {    // find island
                    island++;
                    visit(grid, i, j);
                }
            }
        }
        return island;
    }
    
    /* a helper function that marks the connecting islands to 0  */
    private void visit(char[][] grid, int i, int j) {
        if (grid[i][j] == '0') return;  // no island here
        grid[i][j] = '0';   // change island to 0, indicating that we have visited before
        if (i - 1 >= 0) visit(grid, i-1, j);
        if (i + 1 < grid.length) visit(grid, i+1, j);
        if (j - 1 >= 0) visit(grid, i, j-1);
        if (j + 1 < grid[0].length) visit(grid, i, j+1);
    }
}

// Solution 1: version 1
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