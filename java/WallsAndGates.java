/*
    You are given a m x n 2D grid initialized with these three possible values.

    -1 - A wall or an obstacle.
    0 - A gate.
    INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
    Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

    For example, given the 2D grid:
    INF  -1  0  INF
    INF INF INF  -1
    INF  -1 INF  -1
      0  -1 INF INF
    After running your function, the 2D grid should be:
      3  -1   0   1
      2   2   1  -1
      1  -1   2  -1
      0  -1   3   4
 */
 
// Solution 3: BFS from all gates at the same time
// Similar to previous solution.
// But this time, we do BFS from all gates simultaneously.
// This way, we guarantee to find the closest path for each room.
// Time: O(mn) - we visit every cell at most once
// Space: O(mn) - queue
class Solution {
    final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        Queue<int[]> q = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    q.offer(new int[] {i, j});
                    count++;
                }
            }
        }
        int level = 1;
        int nextCount = 0;
        while (count > 0) {
            int[] index = q.poll();
            count--;
            for (int[] d : dirs) {
                int di = index[0] + d[0];
                int dj = index[1] + d[1];
                if (di<0 || di>=rooms.length || dj<0 || dj>= rooms[0].length) continue;
                if (rooms[di][dj] == Integer.MAX_VALUE) {
                    rooms[di][dj] = level;
                    q.offer(new int[] {di, dj});
                    nextCount++;
                }
            }
            if (count == 0) {
                count = nextCount;
                nextCount = 0;
                level++;
            }
        }
    }
}


// Solution 2: BFS from gates
// BFS from a gate will guarantees the shortest path to that gate.
// However, b/c we don't know which gate is the closest, need to do
// BFS on all gates.
// Time: O(kmn) - each BFS is O(mn), k is the number of gates
// Space: O(mn) - queue
class Solution {
    final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j);
                }
            }
        }
    } 
    private void bfs(int[][] rooms, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i, j});
        int level = 1;    // keep track of distance
        int count = 1;
        int nextCount = 0;
        while (count > 0) {
            int[] index = q.poll();
            count--;
            for (int[] d : dirs) {
                int di = index[0] + d[0];
                int dj = index[1] + d[1];
                if (di<0 || di>=rooms.length || dj<0 || dj>= rooms[0].length) continue;
                if (rooms[di][dj] == -1 || rooms[di][dj] == 0) continue;
                if (rooms[di][dj] > level) {
                    rooms[di][dj] = level;
                    q.offer(new int[] {di, dj});
                    nextCount++;
                }
            }
            if (count == 0) {
                count = nextCount;
                nextCount = 0;
                level++;
            }
        }
    }
}

// Solution 1: DFS
// Brute forcing with DFS, find all possible paths and 
// choose the closet one.
// Time: (m^2*n^2) - Time limit exceeded
// Space: O(mn) - call stack, longest path can be m*n
// 11/14/2017
class Solution {
    final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == Integer.MAX_VALUE) {
                    dfs(rooms, i, j);
                }
            }
        }
    }
    
    private int dfs(int[][] rooms, int i, int j) {
        if (i<0 || i>=rooms.length || j<0 || j>= rooms[0].length) return Integer.MAX_VALUE;
        if (rooms[i][j] == 0) return 0;
        if (rooms[i][j] == -1) return Integer.MAX_VALUE;
        if (rooms[i][j] != Integer.MAX_VALUE) return rooms[i][j];
        int minPath = Integer.MAX_VALUE;
        for (int[] d : dirs) {
            minPath = Math.min(minPath, dfs(rooms, i+d[0], j+d[1]));
        }
        if (minPath == Integer.MAX_VALUE) {
            rooms[i][j] = -1;
        } else {
            rooms[i][j] = minPath+1;
        }
        return rooms[i][j];
    }
}