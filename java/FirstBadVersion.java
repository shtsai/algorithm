/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest 
 * version of your product fails the quality check. Since each version is developed based on the previous 
 * version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the 
 * following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a 
 * function to find the first bad version. You should minimize the number of calls to the API.
 */

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

// Solution 1: Binary Search
// Perform Binary search to repeatedly check the middle version.
// If the middle version is bad, we know all subsequent versions must
// also be bad, so we continue searching on the left half.
// If the middle version is good, we know all previous versions must
// be good, so we continue searching on the right half.
//
// Time: O(logn)
// Space: O(1)
// 11/02/2017
      
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}