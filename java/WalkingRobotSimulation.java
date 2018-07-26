/*
    A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:
    -2: turn left 90 degrees
    -1: turn right 90 degrees
    1 <= x <= 9: move forward x units
    Some of the grid squares are obstacles.
    The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
    If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)
    Return the square of the maximum Euclidean distance that the robot will be from the origin.

    Example 1:
    Input: commands = [4,-1,3], obstacles = []
    Output: 25
    Explanation: robot will go to (3, 4)

    Example 2:
    Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
    Output: 65
    Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)

    Note:
    0 <= commands.length <= 10000
    0 <= obstacles.length <= 10000
    -30000 <= obstacle[i][0] <= 30000
    -30000 <= obstacle[i][1] <= 30000
    The answer is guaranteed to be less than 2 ^ 31.
 */

// Solution 1:
// Store all obstacles in a HashMap for fast retrieval.
// Set four direction.
// Move the robot, check for obstacles, update max at the each step.
//
// Time: O(n) - n:total # of steps
// Space: O(m) - m:# of obstacles
// 07/21/2018

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        HashMap<Integer,HashSet<Integer>> obs = new HashMap<>();
        for (int[] ob : obstacles) {
            if (!obs.containsKey(ob[0])) {
                obs.put(ob[0], new HashSet<>());
            }
            obs.get(ob[0]).add(ob[1]);
        }
        int max = 0;
        int[] current = new int[] {0, 0};
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int index = 0;
        for (int i = 0; i < commands.length; i++) {
            int c = commands[i];
            if (c == -2) {
                index = (index + 3) % 4;
            } else if (c == -1) {
                index = (index + 1) % 4;
            } else {
                for (int j = 0; j < c; j++) {
                    int[] dir = dirs[index];
                    int[] next = new int[2];
                    next[0] = dir[0] + current[0];
                    next[1] = dir[1] + current[1];
                    if (obs.containsKey(next[0]) && obs.get(next[0]).contains(next[1])) {
                        break;
                    } else {
                        current = next;
                        max = Math.max(max, current[0] * current[0] +  current[1] * current[1]);
                    }
                }
            }
        }
        return max;
    }
}
