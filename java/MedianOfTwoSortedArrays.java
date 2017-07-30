/*
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 */

// reference:
// https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {     // make nums1 shorter than nums2
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        int m = nums1.length, n = nums2.length, half = (m + n + 1) / 2;     // add 1 to round up
        int i_max = m, i_min = 0;
        
        while (i_max >= i_min) {
            int i = i_min + (i_max - i_min) / 2, j = half - i;   // make sure # of nums on the left = # of nums on the right
            if (i < m && nums1[i] < nums2[j-1]) i_min = i+1;
            else if (i > 0 && nums2[j] < nums1[i-1]) i_max = i-1;
            else {      // find correct i
                int leftMax, rightMin;
                if (i == 0) leftMax = nums2[j-1];
                else if (j == 0) leftMax = nums1[i-1];
                else leftMax = Math.max(nums1[i-1], nums2[j-1]);
        
                if ((m + n) % 2 == 1) return leftMax;       // if total is odd, the median is the max on the left
        
                if (i == m) rightMin = nums2[j];
                else if (j == n) rightMin = nums1[i];
                else rightMin = Math.min(nums1[i], nums2[j]);
                return (leftMax + rightMin) / 2.0;
            }
        }
        
        return 0;
    }
}