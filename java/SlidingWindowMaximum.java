/*
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * 
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 * 
 * Note: 
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 * 
 * Follow up:
 * Could you solve it in linear time? 
 */

// Solution 2:
// use deque, so that we can check both end
// only maintain the largest elements for a window in the queue
// remove smaller and old elements
// Time: O(n)
// Space: O(k)
// 09/10/2018
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> max = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length) {    // add max
                while(!max.isEmpty() && max.peekLast() < nums[i]) {
                    max.pollLast();
                }
                max.offerLast(nums[i]);
            }
            if (i >= k - 1) {         // store max in window and remove oldest
                res[i - k + 1] = max.peekFirst();
                if (nums[i - k + 1] == max.peekFirst()) {
                    max.pollFirst();
                }
            }
        }
        return res;
    }
}
// Solution 1:
// use priorityqueue to get max
// Time: O(kn) - n iterations, remove in queue is O(k)
// Space: O(k)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k+1];
        if (nums == null || nums.length == 0 || k == 0) return new int[0];
        PriorityQueue<Integer> q = new PriorityQueue<>(k, Collections.reverseOrder());  // get max
        
        for (int i = 0; i < k-1; i++) {    // only keep K-1 elements in priority
            q.offer(nums[i]);
        }
        
        // add new element to the queue, get max, then remove oldest element
        for (int round = 0, i = 0; round < nums.length-k+1; round++) {   // i points to the element that is about to be deleted
            q.offer(nums[round+k-1]);
            res[round] = q.peek();      // get the max of current window
            q.remove(nums[i++]);
        }
        return res;
    }
}