/*
    An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

    Example:

    Input:
    [
      "0010",
      "0110",
      "0100"
    ]
    and x = 0, y = 2

    Output: 6
 */

// Solution 2: Binary search
// Use binary search to find the four corners of the rectangle.
// During each binary step, scan through the entire array to find
// if the column/row has a black pixel. If so, continue searching 
// on the one side, otherwise, search on the other side.
//
// Time: O(mlogn + nlogm)
// Space: O(1)
// version 1: verbose
class Solution {
    public int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        int m = image.length, n = image[0].length;
        int left = findLeft(image, 0, y, 0, m);
        int right = findRight(image, y + 1, n, 0, m);
        int top = findTop(image, 0, x, left, right);
        int buttom = findButtom(image, x + 1, m, left, right);
        return (right -left) * (buttom - top);
    }
    
    public int findLeft(char[][] image, int start, int end, int top, int buttom) {
        while (start < end) {
            int row = top;
            int mid = start + (end - start) / 2;
            while (row < buttom && image[row][mid] == '0') {
                row++;
            }
            if (row < buttom) {  // exist a more left column
                end = mid;
            } else {
                start = mid + 1;   
            }
        }
        return start;
    }
    
    public int findRight(char[][] image, int start, int end, int top, int buttom) {
        while (start < end) {
            int row = top;
            int mid = start + (end - start) / 2;
            while (row < buttom && image[row][mid] == '0') {
                row++;
            }
            if (row < buttom) {  // exist a more right column
                start = mid + 1;
            } else {
                end = mid;   
            }
        }
        return start;
    }
    
    public int findTop(char[][] image, int start, int end, int left, int right) {
        while (start < end) {
            int col = left;
            int mid = start + (end - start) / 2;
            while (col < right && image[mid][col] == '0') {
                col++;
            }
            if (col < right) {  // exist a more top row
                end = mid;
            } else {
                start = mid + 1;   
            }
        }
        return start;
    }
    
    public int findButtom(char[][] image, int start, int end, int left, int right) {
        while (start < end) {
            int col = left;
            int mid = start + (end - start) / 2;
            while (col < right && image[mid][col] == '0') {
                col++;
            }
            if (col < right) {  // exist a more buttom row
                start = mid + 1;
            } else {
                end = mid;   
            }
        }
        return start;
    }
}

// Solution 1: DFS
// Search through all connected black pixel and find the position for
// (min x, max x, min y, max y).
//
// Time: O(mn) - worst case scan the entire image
// Space: O(mn) - visited array
// 05/30/2018

class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int minArea(char[][] image, int x, int y) {
        // {min x, max x, min y, max y}
        int[] corners = {Integer.MAX_VALUE, Integer.MIN_VALUE,
                         Integer.MAX_VALUE, Integer.MIN_VALUE};
        boolean[][] visited = new boolean[image.length][image[0].length];
        dfs(image, x, y, corners, visited);
        return (corners[1] - corners[0] + 1) * (corners[3] - corners[2] + 1);
    }
    
    public void dfs(char[][] image, int x, int y, int[] corners, boolean[][] visited) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length
           || image[x][y] != '1' || visited[x][y]) {
            return;
        } else {
            corners[0] = Math.min(corners[0], x);
            corners[1] = Math.max(corners[1], x);
            corners[2] = Math.min(corners[2], y);
            corners[3] = Math.max(corners[3], y);
            visited[x][y] = true;
            for (int[] dir : directions) {
                dfs(image, x + dir[0], y + dir[1], corners, visited);
            }
        }
    }
}