/*
    Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

    For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 */

// Solution 1 version 2:
// Create a helper function to generate range, much cleaner
// 10/08/2017
    
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            res.add(getRange(lower, upper));
            return res;
        }
        long start = lower;
        for (int num : nums) {
            if (num == start + 1) {
                res.add(getRange(start, num-1));
            } else if (num > start + 1) {
                res.add(getRange(start, num-1));
            }
            start = (long) num + 1;
        } 
        if (start <= upper) {
            res.add(getRange(start, upper));
        }
        return res;
    }
    
    private String getRange(long a, long b) {
        if (a == b) return String.valueOf(a);
        return String.valueOf(a) + "->" + String.valueOf(b);
    }
}

// Solution 1:
// Many corner cases
// Use a variable start to record the next start index.
// Scan through the array, find the gap between start and current num,
// and add it to the result list.
// When finish scanning array, need to check start with the upper bound.
// Also, use long for calculation to get large numbers correct.
// Time: O(n)
// Space: O(1)
// 10/08/2017

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            if (lower == upper) res.add(String.valueOf(lower));
            else res.add(String.valueOf(lower) + "->" + String.valueOf(upper));
            return res;
        }
        long start = lower;
        for (int num : nums) {
            if (num == start + 1) {
                res.add(String.valueOf(start));
            } else if (num > start + 1) {
                String s = String.valueOf(start) + "->" + String.valueOf(num-1);
                res.add(s);
            }
            start = (long) num + 1;
        } 
        if (upper - start == 0) {
            res.add(String.valueOf(upper));
        } else if (upper - start >= 1) {
            res.add(String.valueOf(start) + "->" + String.valueOf(upper));
        }
        return res;
    }
}