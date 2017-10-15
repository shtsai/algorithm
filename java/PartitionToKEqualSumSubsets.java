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

// Solution 1: DFS
// First check sum is divisable by k.
// Sort the input array into descending order.
// Then use DFS to find k occurence of the target value.
// Remove nodes that sums up to target after each iteration so we don't double count.
//
// Time Limit Exceeded
// 10/14/2017

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;     // cannot divide evenly
        int target = sum / k;
        
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        Collections.sort(list, Collections.reverseOrder());  // descending order

        for (int i = 0; i < k; i++) {   // perform k times
            if (!findTarget(list, target, 0)) {
                return false;
            } 
        
        }
        return true;
    }
    
    private boolean findTarget(LinkedList<Integer> list, int target, int current) {

        if (current == target) return true;
        if (current > target) return false;
        if (list.size() == 0) return false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > target-current) continue;
            int add = list.remove(i);
            if (findTarget(list, target, current + add)) return true;
            list.add(i, add);
        }
        
        return false;
    }
}