/*
    Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

    Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

    Example 1:
    Input: [1, 2, 2, 3, 1]
    Output: 2
    Explanation: 
    The input array has a degree of 2 because both elements 1 and 2 appear twice.
    Of the subarrays that have the same degree:
    [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
    The shortest length is 2. So return 2.
    Example 2:
    Input: [1,2,2,3,1,4,2]
    Output: 6
    Note:

    nums.length will be between 1 and 50,000.
    nums[i] will be an integer between 0 and 49,999.
 */

// Solution 1: brute force
// Use a hashmap to count the frequency of each numbers, and find the max degree.
// Then, check all numbers that have the max degree, can compute their length.
// Finally, return the minimal one.
// Time: O(n^2) - worst case n different number, scan n times
// Space: O(n) - hashMap
// 10/14/2017

class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 0);
            }
            map.put(nums[i], map.get(nums[i])+1);
        }
        
        int max = Integer.MIN_VALUE;
        ArrayList<Integer> max_num = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
                max_num.clear();
                max_num.add(key);
            } else if (map.get(key) == max) {
                max_num.add(key);
            }
        }
        
        int res = Integer.MAX_VALUE;
        for (int mx : max_num) {
            int start = -1, end = -1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == mx) {
                    if (start == -1) {
                        start = i;
                        end = i;
                    } else {
                        end = i;
                    }
                }
            }
            res = Math.min(res, end-start+1);
        }
        return res;
    }
}