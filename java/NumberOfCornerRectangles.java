/*
	Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

	A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

	Example 1:
	Input: grid = 
	[[1, 0, 0, 1, 0],
	 [0, 0, 1, 0, 1],
	 [0, 0, 0, 1, 0],
	 [1, 0, 1, 0, 1]]
	Output: 1
	Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
	Example 2:
	Input: grid = 
	[[1, 1, 1],
	 [1, 1, 1],
	 [1, 1, 1]]
	Output: 9
	Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
	Example 3:
	Input: grid = 
	[[1, 1, 1, 1]]
	Output: 0
	Explanation: Rectangles must have four distinct corners.
	Note:
	The number of rows and columns of grid will each be in the range [1, 200].
	Each grid[i][j] will be either 0 or 1.
	The number of 1s in the grid will be at most 6000.
 */

// Solution 1:
// First search for all horizontal lines and store them by
// their lengths and starting column.
// Then scan through the lines that have the same length and starting column,
// add the number of rectangles.
// Time: O(n^3)
// Space: O(n^3)
// 12/16/2017

class Solution {
    private class Point {
        int length;
        int srow, scol;
        public Point (int srow, int scol) {
            this.srow = srow;
            this.scol = scol;
        }
    }
    public int countCornerRectangles(int[][] grid) {
        HashMap<Integer, HashMap<Integer, List<Point>>> hline = new HashMap<>();
        for (int i = 0; i < grid[0].length; i++) {
            hline.put(i, new HashMap<>());
            for (int j = 0; j < grid[0].length; j++) {
                hline.get(i).put(j, new ArrayList<>());
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                for (int k = j + 1; k < grid[0].length; k++) {
                    if (grid[i][k] == 0) continue;
                    hline.get(k-j).get(j).add(new Point(i, j));
                }
            }
        }

        int res = 0;
        for (Integer len : hline.keySet()) {
            HashMap<Integer, List<Point>> sameLen = hline.get(len);
            for (Integer start : sameLen.keySet()) {
                List<Point> hl = sameLen.get(start);
                res += (hl.size() * hl.size() - hl.size())/2;
            }     
        }
        return res;
    }
}