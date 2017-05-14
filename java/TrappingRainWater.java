/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */

// solution 1:
// two pointers, one pass O(n) time
public class Solution {
    public int trap(int[] height) {
        // left and right pointers
        int left = 0, right = height.length - 1; 
        // current max height of the left and right side
        int leftmax = 0, rightmax = 0;
        int water = 0;
        
        while (left < right) {
            // update leftmax and rightmax
            leftmax = Math.max(leftmax, height[left]);
            rightmax = Math.max(rightmax, height[right]);
            
            if (leftmax < rightmax) {  // lower bar is on the left side
                water += leftmax - height[left];    // this amount of water is trapped at this position
                left++;
            } else {
                water += rightmax - height[right];
                right--;
            }
        }
        
        return water;
    }
}

// Solution 2:
// stack solution
// store the heights into a stack in decending order
// the water must be trapped between two bars that are higher than the middle
public class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0, i = 0;
        
        while (i < height.length) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {  // push in decending order
                stack.push(i);
                i++;
            } else {
                int middle = stack.pop();  // middle bar
                if (stack.isEmpty()) continue; // no left bar, skip
                int left = stack.peek();
                int h = Math.min(height[left], height[i]) - height[middle]; // height depends on the lower bar
                int width = i - left - 1;   // right index - left index
                water += h * width;
            }
        }
        return water;
    }
}