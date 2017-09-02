/*
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * 
 * Formally the function should:
 * Return true if there exists i, j, k 
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * 
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * 
 * Given [5, 4, 3, 2, 1],
 * return false.
 */

// Solution 1:
// first find two smallest elements, if can find a number larger than them
// there exists an increasing triplet
// Time: O(n), space: O(1)
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int smallest = Integer.MAX_VALUE, secondSmallest = Integer.MAX_VALUE;
        
        for (int n : nums) {
            if (n <= smallest){
                smallest = n;
            } else if (n <= secondSmallest) {   // smallest < n < secondSmallest
                secondSmallest = n;
            } else {
                return true;    // n is greater than smallest and secondSmallest, which forms a increasing triplet
            }
        }
        return false;
    }
}