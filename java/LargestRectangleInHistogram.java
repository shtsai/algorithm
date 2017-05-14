/*
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 */

public class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        
        int max = 0, i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);      // push heights in accending order
                i++;
            } else {    // i is the right side of the rectangle, previous rectangles wouldn't grow at this point
                int h = stack.pop();
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();      // i is right side, stack.peek() is left side
                int area = heights[h] * width;
                max = Math.max(max, area);
            }
        }
        // reach the end, pop the remaining from the stack and calculate
        while (!stack.isEmpty()) {
            int h = stack.pop();
            int width = stack.isEmpty() ? i : i - 1 - stack.peek();      // i is right side, stack.peek() is left side
            int area = heights[h] * width; 
            max = Math.max(max, area);
        }
        return max;
    }
}