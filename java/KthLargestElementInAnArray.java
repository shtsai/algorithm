/*
 * Find the kth largest element in an unsorted array. 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 */

// Solution 2: Selection algorithm
// Use the idea of partition and pivot that is similiar to quick sort
// Some binary search flavour here.
// Time: O(n) best case, O(n^2) worst case
//       Can use randomization to get good average runtime
// Space: O(1)
// reference: https://discuss.leetcode.com/topic/14597/solution-explained
// 09/08/2018
class Solution {
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int index = partition(nums, left, right);
            if (k == index) {
                return nums[k];
            } else if (index > k) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return nums[k];
    }
    public int partition(int[] nums, int left, int right) {
        int pivot = left, value = nums[pivot];
        int l = left + 1, r = right;
        while (l <= right && r > left) {
            while (l <= right && nums[l] <= value) {
                l++;
            }
            while (r > left && nums[r] > value) {
                r--;
            }
            if (l > r) {
                break;
            }
            swap(nums, l, r);
        }
        swap(nums, pivot, r);
        return r;
    }
    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

// Solution 1: Min heap
// Use a priority queue of size k to keep the largest k numbers
// Time: O(nlogk)
// Space: O(k) - priority queue
// 09/08/2018
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            if (pq.size() < k) {      
                pq.offer(n);
            } else if (pq.size() == k && pq.peek() < n) {
                pq.poll();
                pq.offer(n);
            }
        }
        return pq.peek(); 
    }
}