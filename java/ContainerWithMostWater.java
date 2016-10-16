/*
 *  Given n non-negative integers a1, a2, ..., an, 
 * where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * Find two lines, which together with x-axis forms a container, 
 * such that the container contains the most water.
 */

public class Solution {
    public int maxArea(int[] height) {

        // use two pointers O(n)
        // compare the heights of left and right
        // move the pointer that has smaller height because 
        // that is the only possible way to get a larger value
        int max = 0;
        int current;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            current = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, current);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return max;


        /*
        // brute force O(n^2)
        int max = 0;
        int current;
        
        for (int i = 0; i < height.length; i++) {
            for (int j = i+1; j < height.length; j++) {
                current = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(max, current);
            }
        }
        return max;
        */
    }
}