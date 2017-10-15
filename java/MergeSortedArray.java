/*
 *  Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 */

// Solution 2 version 2:
// merge from right to left
// Time: O(m+n)
// Space: O(1)
// 10/14/2017

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}

// Solution 2: right to left approach
public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int length = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        
        while (i > -1 && j > -1) {
            nums1[length--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        }
        
        // because m > n, if we reach the end of nums2 (j==0), the remaining portion of nums1 is already sorted
        // On the other hand, it we reach the end of nums1 (i==0), we still need to append nums2
        while (j > -1) {
            nums1[length--] = nums2[j--];
        }
    }
}

// Solution 1: left to right approach
// Create a temp array for nums1, then merge.
// Time: O(m+n)
// Space: O(m) - create a temporary array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // first copy and then merge, waste space O(m)
        int[] temp1 = new int[m];
        for (int i = 0; i < m; i++) {
            temp1[i] = nums1[i];
        }
        
        int i = 0;
        int j = 0;
        while (i < m && j < n) {        // merge
            if (temp1[i] < nums2[j]) {
                nums1[i+j] = temp1[i];
                i++;
            } else {                    // temp1[i] >= nums2[j]
                nums1[i+j] = nums2[j];   
                j++;
            }
        }
        if (i == m && j < n) {      // reach end of temp1, append nums2
            for (; j < n; j++) {
                nums1[i+j] = nums2[j];
            }
        }
        if (i < m && j == n) {      // reach end ot nums2, append temp1
            for (; i < m; i++) {
                nums1[i+j] = temp1[i];   
            }
        }
    }
}