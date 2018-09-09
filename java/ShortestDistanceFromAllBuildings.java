/*
	You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

	Each 0 marks an empty land which you can pass by freely.
	Each 1 marks a building which you cannot pass through.
	Each 2 marks an obstacle which you cannot pass through.
	Example:

	Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

	1 - 0 - 2 - 0 - 1
	|   |   |   |   |
	0 - 0 - 0 - 0 - 0
	|   |   |   |   |
	0 - 0 - 1 - 0 - 0

	Output: 7 

	Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
	             the point (1,2) is an ideal empty land to build a house, as the total 
	             travel distance of 3+3+1=7 is minimal. So return 7.
	
	Note:
	There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
 */

// Solution 1: BFS
// Do BFS from each building(1),
// find minimum distance from each building to empty spaces.
// Finally, find spaces with min total distance and is reachable from all buildings.
//
// Time: O(m ^ 2 * n ^ 2)
// Space: O(mn)
// 09/09/2018

class Solution {
    final int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int shortestDistance(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int m = grid.length, n = grid[0].length;
        int[][] distance = new int[m][n];
        int[][] reach = new int[m][n];
        int buildings = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildings++;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[] {i , j});
                    boolean[][] visited = new boolean[m][n];
                    int dist = 1;
                    while (!q.isEmpty()) {
                        int qSize = q.size();
                        while (qSize > 0) {
                            int[] cur = q.poll();
                            
                            for (int[] d : dirs) {
                                int nr = cur[0] + d[0];
                                int nc = cur[1] + d[1];
                                if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && grid[nr][nc] == 0) {
                                    q.offer(new int[] {nr, nc});
                                    distance[nr][nc] += dist;
                                    reach[nr][nc]++;
                                    visited[nr][nc] = true;
                                }
                            }
                            qSize--;
                        }
                        dist++;
                    }
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == buildings) {
                    min = Math.min(min, distance[i][j]);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}