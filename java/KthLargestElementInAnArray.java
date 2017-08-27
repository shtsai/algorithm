/*
 * Find the kth largest element in an unsorted array. 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 */
// improved version of solution 1:
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            q.offer(nums[i]);
            if (q.size() > k) {
                q.poll();
            }
        }
        return q.peek();
    }
}
// Solution 1:
// use a priority queue of size k to keep the largest k numbers
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (pq.size() < k) {       // if the priority queue is not full
                pq.offer(nums[i]);     // add to the queue
            } else {
                int min = pq.peek();   // otherwise, get the min
                if (min < nums[i]) {   // and compare to the current element, if min is smaller
                    pq.poll();         // add the current element to the queue
                    pq.offer(nums[i]);
                }
            }
        }
        return pq.peek();   // return the smallest element in the queue
    }
}