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
// update max in every iteration
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
// base on "Largest Rectangle In Histogram"
// use stacks to record left boundaries and heights
// a little bit slower than solutio 1
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        int[] rectangles = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {           // create histogram
                if (matrix[i][j] == '1') rectangles[j]++;
                else rectangles[j] = 0;
            }
            
            // Largest Rectangle In Histogram
            Stack<Integer> stack = new Stack<>();
            int index = 0;
            while (index < n) {
                if (stack.isEmpty() || rectangles[index] >= rectangles[stack.peek()]) {
                    stack.push(index);
                    index++;
                } else {
                    int height = rectangles[stack.pop()];
                    int width = stack.isEmpty() ? index : index - (stack.peek()+1);
                    max = Math.max(max, height * width);
                }
            }
            while (!stack.isEmpty()) {
                int height = rectangles[stack.pop()];
                int width = stack.isEmpty() ? index : index - (stack.peek()+1);
                max = Math.max(max, height * width);
            }
        }
        return max;
    }
}