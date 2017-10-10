/*
    There is a fence with n posts, each post can be painted with one of the k colors.

    You have to paint all the posts such that no more than two adjacent fence posts have the same color.

    Return the total number of ways you can paint the fence.

    Note:
    n and k are non-negative integers.
 */



// Solution 1:
// Use two variables:
// diffColor = the number of way to paint using a different color than the previous
// sameColor = the number of way to paint using the same color as previous
//
// At each iteration, we can append (k-1) colors to the previous result, regardless
// whether they are diffColor or sameColor.
// However, we can only append the same color to those in diffColor, because it is
// not allowed to have more than two adjacent posts with the same color.
//
// Time: O(n) - one pass
// Space: O(1)
// reference: https://discuss.leetcode.com/topic/23426/o-n-time-java-solution-o-1-space
// 10/10/2017

class Solution {
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        
        int diffColor = k * (k-1);
        int sameColor = k;
        
        for (int i = 2; i < n; i++) {
            int temp = diffColor;
            diffColor = (diffColor + sameColor) * (k-1);
            sameColor = temp;
        }
        
        return diffColor + sameColor;
    }
}
