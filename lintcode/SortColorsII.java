/*
    Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

    Notice:
        You are not suppose to use the library's sort function for this problem.

    k <= n

    Example:
        Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].
 */

// Solution 1: Rainbow sort
// A combination of merge sort and quick sort
// First sort the array into two halves, then recursively sort the two subarray
// Time: O(nlogk) - there are log k levels, each level does O(n) work
// Space: O(1)
// 10/05/2017
        
public class Solution {
    /*
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] colors, int k) {
        sortHelper(colors, 0, colors.length-1, 1, k);
    }
    
    private void sortHelper(int[] colors, int start, int end, int cStart, int cEnd) {
        if (start >= end) return;
        if (cStart == cEnd) return;
        
        // sort the array into two sections
        // left section is less than or equal to mid
        // right section is greater than mid
        int mid = cStart + (cEnd - cStart) / 2;
        int left = start, right = end;
        while (true) {
            while (left <= end && colors[left] <= mid) left++;
            while (right >= start && colors[right] > mid) right--;
            if (left < right) {
                swap(colors, left, right);
            } else {
                break;
            }
        }
        
        // Then recursively sort the two sections
        // right is the index of last element less than mid
        // left is the index of first element greater than mid
        sortHelper(colors, start, right, cStart, mid);
        sortHelper(colors, left, end, mid+1, cEnd);
        
    }
    
    private void swap(int[] C, int a, int b) {
        int temp = C[a];
        C[a] = C[b];
        C[b] = temp;
    }
}