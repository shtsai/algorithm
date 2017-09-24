/*
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * 
 * For example, given three people living at (0,0), (0,4), and (2,2):
 * 
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 */

// Solution 2 version 2:
// Similiar to version 1, but no soring needed
// In fact, we get obtain the array in sorted order
// Time: O(mn)
// Space: O(mn)
// 09/23/2017
class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        ArrayList<Integer> hor = new ArrayList<>();
        ArrayList<Integer> ver = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {     // build the array in sorted order
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ver.add(i);
                }
            }
        }
        for (int j = 0; j < grid[0].length; j++) {  // build the array in sorted order
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] == 1) {
                    hor.add(j);
                }
            }
        }
        int medianIndex = (hor.size()-1)/2;
        int col = hor.get(medianIndex);
        int row = ver.get(medianIndex);
        int totalDist = 0;
        for (int i = 0; i < hor.size(); i++) {
            totalDist += Math.abs(hor.get(i)- col) + Math.abs(ver.get(i)- row);
        }
        return totalDist;
    }
}

// Solution 2: sorting
// Convert 2D problem to two 1D problems
// Search for the median value of the vertical and horizontal axis,
// which is the point where total distance is minimized.
// Time: O(mnlogmn) - worst case mn points, and need sorting
// Space: O(mn)
// Reference: https://leetcode.com/articles/best-meeting-point/
// 09/23/2017
class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        ArrayList<Integer> hor = new ArrayList<>();
        ArrayList<Integer> ver = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    hor.add(j);     // add all value row and col indices
                    ver.add(i);     // to two lists
                }
            }
        }
        int medianIndex = (hor.size()-1)/2;   // index of the median
        Collections.sort(hor);            // sort the array
        int col = hor.get(medianIndex);   // and get the median
        Collections.sort(ver);
        int row = ver.get(medianIndex);
        int totalDist = 0;
        for (int i = 0; i < hor.size(); i++) {  // sum up all distance to median point
            totalDist += Math.abs(hor.get(i)- col) + Math.abs(ver.get(i)- row);
        }
        return totalDist;
    }
}

// Solution 1:
// First find the coordinates of all people.
// Then try every spot on the grid, calculate the total distance
// for everyone to reach this spot, and return the min value
// of the total distance.
//
// Time: O(m^2 * n^2) - m * n grid, can have at most mn people
// Space: O(1)
// 09/23/2017

class Solution {
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        ArrayList<Integer[]> people = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {      // find all people
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    Integer[] person = new Integer[] {i, j};
                    people.add(person);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int totalDist = 0;
                for (Integer[] person : people) {
                    totalDist += Math.abs(person[0]-i) + Math.abs(person[1]-j);
                }
                min = Math.min(min, totalDist);
            }
        }
        
        return min;
    }
}