/*
    Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

    Examples: 
    [2,3,4] , the median is 3

    [2,3], the median is (2 + 3) / 2 = 2.5

    Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

    For example,
    Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

    Window position                Median
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       1
     1 [3  -1  -3] 5  3  6  7       -1
     1  3 [-1  -3  5] 3  6  7       -1
     1  3  -1 [-3  5  3] 6  7       3
     1  3  -1  -3 [5  3  6] 7       5
     1  3  -1  -3  5 [3  6  7]      6
    Therefore, return the median sliding window as [1,-1,-1,3,5,6].

    Note: 
    You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 */

// Solution 1: Two Heaps
// Similar to Find Median From Data Stream
// Use two heaps to maintain numbers larger than and smaller than median.
// Add and remove numbers from the heap as the window moves.
// Time: O(n) - one pass
// Space: O(n) - two heaps
// 10/27/2017

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> large = new PriorityQueue<>();
        PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        double[] res = new double[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            if (large.isEmpty() || nums[i] > large.peek()) {
                large.offer(nums[i]);
            } else {
                small.offer(nums[i]);
            }
            rebalance(large, small);
            if (i >= k-1) {     // complete a window
                // add result to res
                if (large.size() == small.size()) {
                    res[i-k+1] = ((double) large.peek() + small.peek()) / 2.0;
                } else {
                    res[i-k+1] = large.size() > small.size() ? large.peek(): small.peek();
                }
                
                // remove first number within the window
                if (nums[i-k+1] >= large.peek()) {
                    large.remove(nums[i-k+1]);
                } else if (nums[i-k+1] <= small.peek()) {
                    small.remove(nums[i-k+1]);
                }
                rebalance(large, small);
            }
        }
        
        return res;
    }
    
    private void rebalance(PriorityQueue<Integer> large, PriorityQueue<Integer> small) {
        if (large.size() > small.size() + 1) {
            small.offer(large.poll());
        }
        if (small.size() > large.size() + 1 || (large.isEmpty() && !small.isEmpty())) {
            large.offer(small.poll());
        }
    }
}