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
// https://discuss.leetcode.com/topic/19055/java-o-n-solution-using-deque-with-explanation
class Solution {
    public int[] maxSlidingWindow(int[] a, int k) {     
        if (a.length == 0 || k <= 0) return new int[0];
        int[] res = new int[a.length-k+1];
        int index = 0;
        Deque<Integer> q = new ArrayDeque<>();      // use q to store indices
        
        for (int i = 0; i < a.length; i++) {
            while (!q.isEmpty() && q.peek() < i-k+1) {  // remove elements that are no longer in the window
                q.poll();   
            }
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) { // current element > last element in the q
                q.pollLast();                                // element can be removed
            }
            q.offer(i);
            if (i >= k - 1) {       // reach a windows
                res[index] = a[q.peek()];
                index++;
            }
        }
        return res;
    }
}

// Solution 1:
// use priorityqueue to get max
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