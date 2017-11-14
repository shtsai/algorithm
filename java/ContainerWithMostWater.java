/*
 *  Given n non-negative integers a1, a2, ..., an, 
 * where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * Find two lines, which together with x-axis forms a container, 
 * such that the container contains the most water.
 */


// Solution 2: Two pointers
// Start with a left and right pointer, and calculate the area.
// Compare the heights of left and right,
// move the pointer that has smaller height because 
// that is the only possible way to get a larger value.
// Update max area accordingly.
//
// Time: O(n) - one pass
// Space: O(1)
// 11/14/2017
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length-1;
        while (left < right) {
            max = Math.max(max, (right-left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}


// Solution 1: Brute force solution
// Try all n^2 combinations
// Time: O(n^2)
// Space: O(1)
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, (j-i) * Math.min(height[i], height[j]));
            }
        }
        return max;
    }
}