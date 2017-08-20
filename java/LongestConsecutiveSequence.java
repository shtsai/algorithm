/*
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * 
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 */

// dynamic programming
// use hashmap to do O(1) search, memoize the result in the map
// time: O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums) map.put(i, -1); // initialize map, -1: not calculated yet  
        for (int i : nums) max = Math.max(max, getLargerConsecutive(map, i));
        return max;
    }
    
    // this function returns the number of consecutive numbers that are larger than i
    private int getLargerConsecutive(HashMap<Integer, Integer> map, int i) {
        if (map.get(i) != -1) return map.get(i);    // already calculated
        int res = 1;
        if (map.containsKey(i+1)) res = getLargerConsecutive(map, i+1)+1;   // consecutive number found
        map.put(i, res);    // update map
        return res;
    }
}

// Solution 1: sort and then count
// time: O(nlgn)
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);

        int max = 1, count = 1, prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prev) continue;
            else if (nums[i] == prev + 1) {  // find a consecutive
                count++;
                prev = nums[i];
            } else {
                max = Math.max(max, count);
                count = 1;
                prev = nums[i];
            }
        }
        return Math.max(max, count);
    }
}