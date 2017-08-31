/*
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 */

// Solution 2:
// one stack
public class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        
        int max = 0, i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i);      // push heights in accending order
                i++;
            } else {    // i is the right side of the rectangle, previous rectangles wouldn't grow at this point
                int h = stack.pop();
                int width = stack.isEmpty() ? i : i - (1+stack.peek());      // i is right side, stack.peek()+1 is left side
                max = Math.max(max, heights[h] * width);
            }
        }
        // reach the end, pop the remaining from the stack and calculate
        while (!stack.isEmpty()) {
            int h = stack.pop();
            int width = stack.isEmpty() ? i : i - (1+stack.peek());      // i is right side, stack.peek() is left side
            max = Math.max(max, heights[h] * width);
        }
        return max;
    }
}


// Solution 1:
// use two stacks, lefts and h
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int max = 0;
        Stack<Integer> lefts = new Stack<Integer>();      // a stack that stores left indices of rectangles
        Stack<Integer> h = new Stack<Integer>();      // a stack that stores heights of rectangles
        for (int i = 0; i < heights.length; i++) {
            // push higher bars to the stack
            // find a smaller bar, area is impossible to grow, calculate the ones in the stack
            int prevLeft = -1;
            while (!h.isEmpty() && h.peek() > heights[i]) {
                int left = lefts.pop();     // left boundary of the rectangle
                int height = h.pop();       // height of the rectangle
                max = Math.max(max, (i-left)*height);
                prevLeft = left;
            }
            if (prevLeft == -1) {   // never enter while loop, heights[i] > h.peek()
                lefts.push(i);
                h.push(heights[i]);
            } else {                // entered while loop, left now is prevLeft
                lefts.push(prevLeft);
                h.push(heights[i]);
            }
        }
        while (!h.isEmpty()) {     // there are still bars in the stack
            int right = heights.length;     // set right boundary to the rightmost position
            int left = lefts.pop();
            int height = h.pop();
            max = Math.max(max, (right-left)*height);
        }
        return max;
    }
}