/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */

// reference: https://leetcode.com/problems/trapping-rain-water/solution/


// Solution 4: Improved DP, two pointers
// Time: O(n)
// Space: O(1)
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

// Solution 3: stack 
// Store the heights into a stack in decending order.
// Water must be trapped between two bars that are higher than the middle
//
// Time: O(n)
// Space: O(n)
// version 2:
// 11/14/2017
class Solution {
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i);
            } else {
                while (height[i] > height[stack.peek()]) {
                    int lower = stack.pop();
                    if (stack.isEmpty()) {
                        break;
                    }
                    int h = Math.min(height[stack.peek()], height[i]) - height[lower];
                    int width = i - stack.peek() - 1;
                    water += h * width;
                }
                stack.push(i);
            }
        }
        return water;
    }
}

// version 1:
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




// solution 2: DP, 
// Use DP array to memoize maxL and maxR at each index.
// Same idea as solution 1
// Time: O(n)
// Space: O(n) - array
// 11/14/2017
class Solution {
    public int trap(int[] height) {
        int water = 0;
        int[] maxL = new int[height.length];
        int[] maxR = new int[height.length];
        for (int i = 1; i < height.length; i++) {
            maxL[i] = Math.max(maxL[i-1], height[i-1]);
        }
        for (int i = height.length-2; i >= 0; i--) {
            maxR[i] = Math.max(maxR[i+1], height[i+1]);
        }
        for (int i = 1; i < height.length-1; i++) {
            if (Math.min(maxL[i], maxR[i]) - height[i] > 0) {
                water += Math.min(maxL[i], maxR[i]) - height[i];
            }
        }
        return water;
    }
}

// Solution 1: Brute force
// Scan through the array, for each index, find the 
// max height on its left and on its right. 
// The water trapped in this index is the difference
// between min(maxL, maxR) and the height of this index.
// B/c lower bar determines the amount of water trapped.
//
// Time: O(n^2)
// Space: O(1)
// 11/14/2017
class Solution {
    public int trap(int[] height) {
        int water = 0;
        for (int i = 1; i < height.length-1; i++) {
            int maxL = 0, maxR = 0;
            for (int j = i; j >= 0; j--) {
                maxL = Math.max(maxL, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                maxR = Math.max(maxR, height[j]);
            }
            water += Math.min(maxL, maxR) - height[i];
        }
        return water;
    }
}
