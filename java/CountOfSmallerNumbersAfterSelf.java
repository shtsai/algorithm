/*
	You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

	Example:

	Input: [5,2,6,1]
	Output: [2,1,1,0] 
	Explanation:
	To the right of 5 there are 2 smaller elements (2 and 1).
	To the right of 2 there is only 1 smaller element (1).
	To the right of 6 there is 1 smaller element (1).
	To the right of 1 there is 0 smaller element.
 */

// Solution 2: Merge sort
// Similar to count inversion, but return inversion for each index.
// Perform regular merge sort, but sort on index.
// During merge process, keep track of how many elements on the right array is smaller than current num.
// Reference: https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76583/11ms-JAVA-solution-using-merge-sort-with-explanation
//
// Time: O(nlogn)
// Space: O(n) - temp array
// 08/31/2018
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int[] count = new int[nums.length];
        int[] indices = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indices[i] = i;
        }
        mergeSort(nums, indices, count, 0, nums.length - 1);
        
        List<Integer> res = new ArrayList<>();
        for (int c : count) {
            res.add(c);
        }
        return res;
    }
    
    private void mergeSort(int[] nums, int[] indices, int[] count, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(nums, indices, count, left, mid);
            mergeSort(nums, indices, count, mid + 1, right);
            merge(nums, indices, count, left, mid + 1, right);
        }
    }
    
    private void merge(int[] nums, int[] indices, int[] count, int left, int mid, int right) {
        int lIndex = left;
        int rIndex = mid;
        int sortedIndex = 0;
        int rightCount = 0;
        int[] temp = new int[right - left + 1];
    
        while (lIndex < mid && rIndex <= right) {
            if (nums[indices[lIndex]] <= nums[indices[rIndex]]) {
                temp[sortedIndex] = indices[lIndex];
                count[indices[lIndex]] += rightCount;
                lIndex++;
            } else {
                temp[sortedIndex] = indices[rIndex];
                rightCount++;
                rIndex++;
            }
            sortedIndex++;
        }
        
        while (lIndex < mid) {
            temp[sortedIndex] = indices[lIndex];
            count[indices[lIndex]] += rightCount;
            lIndex++;
            sortedIndex++;
        }
        while (rIndex <= right) {
            temp[sortedIndex] = indices[rIndex];
            sortedIndex++;
            rIndex++;
        }
        for (int l = 0; l <= right - left; l++) {
            indices[l + left] = temp[l];
        }
    }
}

// Solution 1: Brute force
// Time: O(n ^ 2)
// Space: O(1)
// 08/30/2018

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            res.add(count);
        }
        return res;
    }
}
