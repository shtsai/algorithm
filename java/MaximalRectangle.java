/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * 
 * For example, given the following matrix:
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 6.
 * 
 */

// Solution 1:
// Dynamic programming, use arrays to store left boundary, right boundary and height
// - left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
// - right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
// - height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
// - height(i,j) = 0, if matrix[i][j]=='0'
// update max in every iteration
// Note the value for right array.
//
// Time: O(mn)
// Space: O(n)
// 09/03/2018
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[] height = new int[n];  // record height
        int[] left = new int[n];    // record the left boundary of rectangles
        int[] right = new int[n];   // record the right boundary
        for (int i = 0; i < n; i++) right[i] = n;	// initialize right to all n

        for (int i = 0; i < m; i++) {
            int curLeft = 0, curRight = n;
            for (int j = 0; j < n; j++) {        // left to right
                if (matrix[i][j] == '1') {
                    height[j] += 1;
                    left[j] = Math.max(left[j], curLeft);  // left boundary can be the previous one or the one on current level
                } else {
                    height[j] = 0;  // discard previous height 
                    left[j] = 0;   // no left boundary
                    curLeft = j+1;  // the left boundary of current level is possible on next position       
                }
            }
            for (int j = n-1; j >= 0; j--) {     // right to left
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], curRight);
                else {
                    right[j] = n;  // same for right
                    curRight = j;
                }
            }
            for (int j = 0; j < n; j++) {
                max = Math.max(max, (right[j]-left[j]) * height[j]);
            }
        }
        return max;
    }
}

// Solution 2:
// Build histogram level by level
// Then use algorithm in "Largest Rectangle In Histogram"
// use stacks to record left boundaries and heights
// Time: O(mn)
// Space: O(n)
// 09/03/2018
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] histogram = new int[matrix[0].length + 1];     // add a zero at the end for ending
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            // build histogram
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    histogram[j]++;
                } else {
                    histogram[j] = 0;
                }
                
            }
            max = Math.max(max, findMaxRectangleInHistogram(histogram));
        }
        return max;
    }
    
    private int findMaxRectangleInHistogram(int[] histogram) {
        Stack<Integer> stack = new Stack<>();
        int right = 0, max = 0;
        while (right < histogram.length) {
            int rightH = histogram[right];
            while (!stack.isEmpty() && histogram[stack.peek()] > rightH) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int h = histogram[mid];
                int w = right - left - 1;
                max = Math.max(max, h * w);
            }
            stack.push(right);
            right++;
        }
        return max;
    }
}