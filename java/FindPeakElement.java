/*
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */

// Solution 1: sequential search
// O(n) time
public class Solution {
    public int findPeakElement(int[] nums) {
        
        // because nums[-1] = -infinity
        // so only need to compare the other side
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]) {
                return i-1;
            }
        }
        // else return the last element
        return nums.length-1;
    }
}


// Solution 2: binary search
// Time: O(log(n))
public class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length-1;
        
        while (low < high) {
            int mid = low + (high - low)/2;
            if (nums[mid] < nums[mid+1]) {
                low = mid+1;
            } else {
                high = mid;
            }
            
        }
        return low;
    }
}