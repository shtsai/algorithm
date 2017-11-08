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

// Solution 1: Binary search
// Given two sorted arrays (assume nums1 is always shorter):
// nums1: [1, 2, 4]
// nums2: [1, 2, 3, 5, 6]
// We can divide them into two parts: left and right.
// Each part will contain "half"=((m + n + 1)/2) elements.
// Note if total number is odd, left half will have one more element.
//
// Now we do binary search on nums1 to find break point.
// Possible range is iMin = 0, iMax = 3.
// (1) i = (iMin + iMax)/2 = 3/2 = 1.
//     Since left half needs (m+n+1)/2 = (3+5+1)/2 = 4 elements,
//     j = half - i = 4 - 1 = 3.
//     Here is the division at this point:
//          nums1: [1,|2, 4]
//          nums2: [1, 2, 3,|5, 6]
//     Left part has four numbers: [1, 1, 2, 3]
//     Right part has four numbers: [2, 4, 5, 6]
//     Next we check if this is a correct division.
//     Since two arrays are already sorted, we just need to compare elements
//     across the array.
//     The largest left element in nums1 is 1, which is smaller than 5. Good!
//     The largest left element in nums2 is 3, which is greater than 2. Bad!!
//     This means that nums1[i] is too small, we need to increment it.
//     So we update iMin = i + 1 = 2 as in binary search.
// 
// (2) i = (iMin + iMax)/2 = 5/2 = 2.
//     j = half - i = 4 - 2 = 2.
//     Here is the division at this point:
//          nums1: [1, 2,|4]
//          nums2: [1, 2,|3, 5, 6]
//     We perform the checks again.
//     The largest left element in nums1 is 2, which is smaller than 3. Good!
//     The largest left element in nums2 is 2, which is smaller than 4. Good!
//     We have found the correct breaking point.
//
//     If total length is odd, then median is the largest element on the left,
//     because left half has one more element.
//     If total length is even, then median is the average of the sum of 
//     leftMax and rightMin.
//
// Note that we need to be careful with indexing here.
//
// Time: O(logn(m))
// Space: O(1)
// 11/08/2017

// version 2:
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {  // make nums1 to be the shorter array
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int m = nums1.length, n = nums2.length;

        int iMin = 0, iMax = m;
        int half = (m + n + 1) / 2;  // # of element on each half
        while (iMin <= iMax) {
            int i = iMin + (iMax-iMin)/2;
            int j = half - i;
            if (i > 0 && nums1[i-1] > nums2[j]) {
                iMax = i-1;
            } else if (i < m && nums2[j-1] > nums1[i]) { // i has not reached the end
                iMin = i+1;
            } else {  // i is good here
                int leftMax, rightMin;
                if (i == 0) {   // find left
                    leftMax = nums2[j-1];
                } else if (j == 0) {
                    leftMax = nums1[i-1];
                } else {
                    leftMax = Math.max(nums1[i-1], nums2[j-1]);
                }
                // if odd, return leftMax, b/c left has one more element than right
                if ((m + n) % 2 == 1) return leftMax; 

                if (i == m) {   // find right
                    rightMin = nums2[j];
                } else if (j == n) {
                    rightMin = nums1[i];
                } else {
                    rightMin = Math.min(nums1[i], nums2[j]);
                }
                return (leftMax + rightMin) / 2.0;
            }
        }
        return -1;
    }
}

// version 1:
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