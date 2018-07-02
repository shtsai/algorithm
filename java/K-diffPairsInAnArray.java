/*
	Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

	Example 1:
	Input: [3, 1, 4, 1, 5], k = 2
	Output: 2
	Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
	Although we have two 1s in the input, we should only return the number of unique pairs.
	Example 2:
	Input:[1, 2, 3, 4, 5], k = 1
	Output: 4
	Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
	Example 3:
	Input: [1, 3, 1, 5, 4], k = 0
	Output: 1
	Explanation: There is one 0-diff pair in the array, (1, 1).
	Note:
	The pairs (i, j) and (j, i) count as the same pair.
	The length of the array won't exceed 10,000.
	All the integers in the given input belong to the range: [-1e7, 1e7].
 */

// Solution 3: HashMap
// Time: O(n)
// Space: O(n)
// 07/01/2018

class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1); 
            }
        }
        int res = 0;
        for (int n : map.keySet()) {
        	// Only look for (n + k) to avoid double counting
            if (k != 0 && map.containsKey(n + k)) {
                res++;
            } else if (k == 0 && map.get(n) > 1) {
                res++;
            }
        }
        return res;
    }
}

// Solution 2: Sort and binary search
// Time: O(nlogn)
// Space: O(1)
// 07/02/2018

class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { // skip duplicate number
                continue;
            }
            if (binarySearch(nums, i+1, nums.length-1, nums[i] + k)) {
                res++;
            } 
        }
        return res;
    }
    
    public boolean binarySearch(int[] array, int left, int right, int target) {
        if (left > right) {
            return false;
        } 
        while (left + 1 <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return true;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        if (array[left] == target) {
            return true;
        } else if (left + 1 <= right && array[left + 1] == target) {
            return true;
        } else {
            return false;
        }
    }
}

// Solution 1: Sort and nested for loops
// Time: O(n^2)
// Space: O(1)
// 07/01/2018

class Solution {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { // skip duplicate number
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == k) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}