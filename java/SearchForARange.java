/*
 * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 */

// solution 2:
// do two binary searches to find the left boundary and right boundary
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
    
        // find left boundary
        int start = 0, end = nums.length-1;
        while (start < end) {
            int mid = start + (end - start) / 2;  // this mid is biased toward left
            if (nums[mid] < target) start = mid+1;
            else end = mid;
        }
        result[0] = start;
        
        // target not exist
        if (nums[start] != target) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        
        // find right boundary
        // no need to reset start to 0 here
        end = nums.length-1;
        while (start < end) {
            int mid = start + (end - start) / 2 + 1; // this mid is biased toward left
            if (nums[mid] <= target) start = mid;
            else end = mid - 1;
        }
        result[1] = start;
        
        return result;
        
    }
}

// Solution 1:
// do binary search to find one of the target values
// then search toward left and right to obtain the target range
public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
    
        int start = 0, end = nums.length-1;

        // binary search
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
                break;
            }
            else if (nums[mid] < target) start = mid+1;
            else end = mid;
        }
        
        // target not found
        if (nums[start] != target) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        
        // search toward left and right to obtain the targe range
        int i = start;
        while (i - 1 > -1 && nums[i-1] == nums[i]) i--;
        while (start + 1 < nums.length && nums[start+1] == nums[start]) start++;
        result[0] = i;
        result[1] = start;
        return result;
        
    }
}