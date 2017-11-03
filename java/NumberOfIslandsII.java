/*
    A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

    Example:

    Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
    Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

    0 0 0
    0 0 0
    0 0 0

    Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

    1 0 0
    0 0 0   Number of islands = 1
    0 0 0

    Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

    1 1 0
    0 0 0   Number of islands = 1
    0 0 0

    Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

    1 1 0
    0 0 1   Number of islands = 2
    0 0 0

    Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

    1 1 0
    0 0 1   Number of islands = 3
    0 1 0

    We return the result as an array: [1, 1, 2, 3]

    Challenge:

    Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */

// Solution 1 version 2:
// 11/03/2017
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int count = 0;
        int[] root = new int[m * n];
        Arrays.fill(root, -1);
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int[] p : positions) {
            int i = p[0], j = p[1];
            root[i * n + j] = i * n + j;    // create a new node
            count++;
            for (int[] d : dirs) {
                int r = i+d[0], c = j+d[1];
                if (r >= 0 && r < m && c >= 0 && c < n) {   // index is valid
                    if (root[r * n + c] != -1) {  // there exists a node
                        if (union(root, i * n + j, r * n + c)) {
                            count--;
                        }
                    }
                }
            }
            res.add(count);
        }
        return res;
    }
    
    /* returns true when union the two component */
    private boolean union(int[] root, int i, int j) {
        int ir = find(root, i);
        int jr = find(root, j);
        if (ir != jr) {
            root[ir] = jr;
            return true;
        } else {
            return false;
        }
    }
    
    private int find(int[] root, int i) {
        while (root[i] != i) {
            i = root[i];
        }
        return i;
    }
}

// Solution 1: Union Find
// Store each island into sets, where sets are originized into trees.
// When adding a new island, check if there are other island connected to it.
// If so, union the two island by adding the new island as a node to the tree
// of the larger island.
// 
// Time: O(k log mn) - union is O(1), just change the root of a set
//                   - find is O(log mn), find the root in a tree
//                   - Multiply above by k iteration
// Space: O(mn)
// reference: https://discuss.leetcode.com/topic/29613/easiest-java-solution-with-explanations
// 10/13/2017

class Solution {
    final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0) return res;
        
        int[] roots = new int[m*n];
        Arrays.fill(roots, -1);
        int count = 0;
        for (int[] p : positions) {
            int root = p[0] * n + p[1];
            roots[root] = root;     // create a new set for the new node
            count++;
            
            for (int[] dir : dirs) {
                int a = p[0] + dir[0];
                int b = p[1] + dir[1];
                int neighbor = a * n + b;
                
                if (a < 0 || a >= m || b < 0 || b >= n || roots[neighbor] == -1) continue;
                neighbor = findRoot(roots, neighbor);
                if (neighbor != root) {  // union the two sets
                    roots[root] = neighbor;
                    root = neighbor;
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }
    
    /* This function finds the highest root of a set (island) */
    private int findRoot(int[] roots, int neighbor) {
        while (roots[neighbor] != neighbor) {
            neighbor = roots[neighbor];
        }
        return neighbor;
    }    
}