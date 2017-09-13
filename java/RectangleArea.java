/*
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * 
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * 
 * Assume that the total area is never beyond the maximum possible value of int.
 */

// Solution 1:
// First find the border of the overlapped rectangle.
// Need to be careful with the case when there is no overlap.
// Then subtract the overlapped area from the sum of area1 and area2.

class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int overlap = 0;
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int buttom = Math.max(B, F);
        int top = Math.min(D, H);
        if (left < right && buttom < top) {   // if condition is false, there is no overlap
            overlap = (right-left) * (top-buttom);
        }

        // area of rectangle 1 + 2       - overlapped area
        return (A-C)*(B-D) + (E-G)*(F-H) - overlap;
    }
}