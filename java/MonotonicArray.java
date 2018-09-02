/*
    An array is monotonic if it is either monotone increasing or monotone decreasing.
    An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
    Return true if and only if the given array A is monotonic.

    Example 1:
    Input: [1,2,2,3]
    Output: true
    
    Example 2:
    Input: [6,5,4,4]
    Output: true
    
    Example 3:
    Input: [1,3,2]
    Output: false

    Example 4:
    Input: [1,2,4,5]
    Output: true

    Example 5:
    Input: [1,1,1]
    Output: true
    
    Note:
    1 <= A.length <= 50000
    -100000 <= A[i] <= 100000
 */

// Solution 1: One-pass
// Find first non-zero difference,
// check if all subsequence diffs equals to that.
//
// Time: O(n)
// Space: O(1)
// 09/02/2018
class Solution {
    public boolean isMonotonic(int[] A) {
        int diff = 0;
        for (int i = 1; i < A.length; i++) {
            int d = Integer.compare(A[i], A[i - 1]);
            if (d != 0) {
                if (diff == 0) {
                    diff = d;
                } else if (diff != d) {
                    return false;
                }
            }
        }
        return true;
    }
}

// Solution 1: Two-pass
// Time: O(n)
// Space: O(1)
// 09/01/2018

class Solution {
    public boolean isMonotonic(int[] A) {
        int count = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] - A[i - 1] >= 0) {
                count++;
            }
        }
        if (count == A.length - 1) {
            return true;
        }
        count = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] - A[i - 1] <= 0) {
                count++;
            }
        }
        return count == A.length - 1;
    }
}