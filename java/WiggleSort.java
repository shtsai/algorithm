/*
 *  Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * 
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4]. 
 */

// Solution 1 version 2:
// Very consise version
// reference: https://discuss.leetcode.com/topic/23871/java-o-n-solution/3
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i=1; i<nums.length; i++) {
            int a = nums[i-1];
            if ((i%2 == 1) == (a > nums[i])) {  // note the use of "==" here
                nums[i-1] = nums[i];
                nums[i] = a;
            }
        }
    }
}

// Solution 1: Greedy
// Iteratively build up solution.
//
// At any point i, we know nums[0:i] is already wiggle sorted.
// There are two possible cases:
//     1. i is odd, therefore it needs be larger than its neighbors
//          (a) if nums[i-1] < nums[i], then it is already wiggle sorted
//          (b) if nums[i-1] > nums[i]:
//              We know that nums[i-2] > nums[i-1] by wiggle sort property.
//              So we can swap nums[i-1] and nums[i] to satisfy requirement.
//     2. i is even, need to be smaller than neighbors
//          (a) if nums[i-1] > nums[i], we are done
//          (b) if nums[i-1] < nums[i]:
//              We know that nums[i-2] < nums[i-1], so just swap nums[i-1] and nums[i]
//
// Time: O(n) - one pass
// Space: O(1)
//
// reference: https://discuss.leetcode.com/topic/42605/my-explanations-of-the-best-voted-algo
// 09/22/2017
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {   // odd index, need to be greater than neighbors
                if (nums[i] < nums[i-1]) {
                    swap(nums, i, i-1);
                } // else it is already wiggle sorted b/c nums[i-1] < nums[i]
            } else {    // even index, need to be smaller than neighbors
                if (nums[i] > nums[i-1]) {
                    swap(nums, i, i-1);
                } // else it is already wiggle sorted b/c nums[i-1] > nums[i]
            }
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}