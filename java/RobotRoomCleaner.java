/*
	Given a robot cleaner in a room modeled as a grid.

	Each cell in the grid can be empty or blocked.

	The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

	When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

	Design an algorithm to clean the entire room using only the 4 given APIs shown below.

	interface Robot {
	  // returns true if next cell is open and robot moves into the cell.
	  // returns false if next cell is obstacle and robot stays on the current cell.
	  boolean move();

	  // Robot will stay on the same cell after calling turnLeft/turnRight.
	  // Each turn will be 90 degrees.
	  void turnLeft();
	  void turnRight();

	  // Clean the current cell.
	  void clean();
	}

	Example:
	Input:
	room = [
	  [1,1,1,1,1,0,1,1],
	  [1,1,1,1,1,0,1,1],
	  [1,0,1,1,1,1,1,1],
	  [0,0,0,1,0,0,0,0],
	  [1,1,1,1,1,1,1,1]
	],
	row = 1,
	col = 3

	Explanation:
	All grids in the room are marked by either 0 or 1.
	0 means the cell is blocked, while 1 means the cell is accessible.
	The robot initially starts at the position of row=1, col=3.
	From the top left corner, its position is one row below and three columns right.
	Notes:

	The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
	The robot's initial position will always be in an accessible cell.
	The initial direction of the robot will be facing up.
	All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
	Assume all four edges of the grid are all surrounded by wall.
 */

/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

// Solution 1: DFS + Hashset
// Set intial index to (0,0)
// Then do DFS to four directions.
// Use a visited data structure to keep track of visited cells.
//
// Time: O(4 ^ n)
// Space: O(n)
// 08/27/2018

// Version 2: HashMap<Integer, HashSet<Integer>>
class Solution {
    int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    HashMap<Integer, HashSet<Integer>> visited;
    
    public void cleanRoom(Robot robot) {
        visited = new HashMap<>();
        clean(robot, visited, 0, 0, 0);
    }
    
    private void clean(Robot robot, HashMap<Integer, HashSet<Integer>> visited, int dirIndex, int r, int c) {
        if (visited.computeIfAbsent(r, x -> new HashSet<>()).contains(c)) {
            return;
        }
        robot.clean();
        visited.computeIfAbsent(r, x -> new HashSet<>()).add(c);
        for (int i = 1; i <= 4; i++) {
            int newDir = (dirIndex + i) % 4;
            robot.turnRight();
            if (robot.move()) {
                int[] dir = dirs[newDir];
                clean(robot, visited, newDir, r + dir[0], c + dir[1]);
                // backtrack
                robot.turnRight();   
                robot.turnRight();
                robot.move();
                robot.turnRight();   
                robot.turnRight();
            }
        }
    }
}

// Version 1: Convert index to string
class Solution {
    int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    HashSet<String> visited;
    
    public void cleanRoom(Robot robot) {
        visited = new HashSet<>();
        clean(robot, visited, 0, 0, 0);
    }
    
    private void clean(Robot robot, HashSet<String> visited, int dirIndex, int r, int c) {
        if (visited.contains(r + "," + c)) {
            return;
        }
        robot.clean();
        visited.add(r + "," + c);
        for (int i = 1; i <= 4; i++) {
            int newDir = (dirIndex + i) % 4;
            robot.turnRight();
            if (robot.move()) {
                int[] dir = dirs[newDir];
                clean(robot, visited, newDir, r + dir[0], c + dir[1]);
                
                // backtrack
                robot.turnRight();   
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
        }
    }
}