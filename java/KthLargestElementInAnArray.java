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
// 09/22/2017

class Solution {
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;
        int low = 0, high = nums.length-1;
        while (low < high) {
            int index = partition(nums, low, high);
            if (index == k) {   // it is the kth 
                break;
            } else if (index > k) {
                high = index-1;
            } else {
                low = index+1;
            }
        }
        return nums[k];     // the Kth largest number that be moved to its correct position
    }
    
    /* This function uses number at index low as the pivot,
       move it to its sorted position, and make sure numbers
       on its left are smaller and numbers on its right are
       larger.                                          */    
    private int partition(int[] nums, int low, int high) {
        int i = low + 1, j = high, pivot = nums[low];
        while (true) {
            while (i < high) {
                if (nums[i] > pivot) {  // find the number on the left part that is greater than the pivot
                    break;
                } else {
                    i++;
                }
            }
            while (j > low) {
                if (nums[j] < pivot) {  // find the number on the right part that is less than the pivot
                    break;
                } else {
                    j--;
                }
            }
            if (i >= j) break;  // invalid indices
            swap(nums, i, j);
        }
        swap(nums, j, low);     // low is the index of the pivot, move it to its correct position
        return j;   // return the ranking of pivot
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

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
// Time: O(nlogk)
// Space: O(k) - priority queue
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