/*
    Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

    Example 1:
    Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
    Output: True
    Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
    Note:

    1 <= k <= len(nums) <= 16.
    0 < nums[i] < 10000.
 */

// Solution 2: Memoization
// Use bits to indicate each possible combinations in `used`.
// For example, 010 means the second element is used.
// Therefore, there are 2 ^ n possible combinations.
// Use a memo array to store intermediate results.
// Time: O(n * 2 ^ n)
// Space: O(2 ^ n)
// 09/22/2018

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) {
            return false;
        }
        Boolean[] memo = new Boolean[1 << nums.length];
        memo[memo.length - 1] = true;   // all 1s
        return search(nums, sum / k, 0, sum, memo);
    }
    
    private boolean search(int[] nums, int target, int used, int todo, Boolean[] memo) {
        if (memo[used] != null) {
            return memo[used];
        }
        memo[used] = false;
        int t = (todo - 1) % target + 1;
        for (int i = 0; i < nums.length; i++) {
            if ((used & (1 << i)) == 0 && nums[i] <= t) {   // haven't used this digit
                int newUsed = used | (1 << i);
                if (search(nums, target, newUsed, todo - nums[i], memo)) {
                    memo[used] = true;
                    break;
                }
            }
        }
        return memo[used];
    }
}

// Solution 1: Backtracking Search
// Time: O(k ^ n)
// Space: O(n)
// 09/22/2018

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        Arrays.sort(nums);
        int index = nums.length - 1;
        if (nums[index] > target) {
            return false;
        }
        
        while (index >= 0 && nums[index] == target) {
            index--;
            k--;
        }
        return search(nums, k, target, new int[k], index);
    }
    
    private boolean search(int[] nums, int k, int target, int[] groups, int index) {
        if (index < 0) {    // base case
            return true;
        }
        int v = nums[index];
        index--;
        for (int g = 0; g < groups.length; g++) {
            if (groups[g] + v <= target) {
                groups[g] += v;
                if (search(nums, k, target, groups, index)) {
                    return true;
                }
                groups[g] -= v;
            }
            if (groups[g] == 0) {   // avoid repeated search
                break;
            }
        }
        return false;
    }
}