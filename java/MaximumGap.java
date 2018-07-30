/*
	Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

	Return 0 if the array contains less than 2 elements.

	Example 1:

	Input: [3,6,9,1]
	Output: 3
	Explanation: The sorted form of the array is [1,3,6,9], either
	             (3,6) or (6,9) has the maximum difference 3.
	Example 2:

	Input: [10]
	Output: 0
	Explanation: The array contains less than 2 elements, therefore return 0.
	Note:

	You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
	Try to solve it in linear time/space.
 */

// Solution 2: Radix sort
// https://en.wikipedia.org/wiki/Radix_sort
// Time: O(n * log_10(n)) = O(n)
// Space: O(n)
// 07/28/2018
class Solution {
    public int maximumGap(int[] nums) {
        nums = radixSort(nums);
        int maxDiff = 0;
        for (int i = 1; i < nums.length; i++) {
            maxDiff = Math.max(maxDiff, nums[i] - nums[i - 1]);
        }
        return maxDiff;
    }
    
    private int[] radixSort(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            max = Math.max(max, n);
        }
        
        int radix = 10, iter = 0;
        while (Math.pow(radix, iter) <= max) {
            list = bucketToList(listToBucket(list, radix, iter));
            iter++;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private List<List<Integer>> listToBucket(List<Integer> list, int radix, int iter) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < radix; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        for (int n : list) {
            int digit = (n / (int) Math.pow(radix, iter)) % radix;
            buckets.get(digit).add(n);
        }
        return buckets;
    }
    
    private List<Integer> bucketToList(List<List<Integer>> buckets) {
        List<Integer> res = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            for (Integer n : bucket) {
                res.add(n);
            }
        }
        return res;
    }
}

// Solution 1: Sort
// Time: O(nlogn)
// Space: O(1)
// 07/28/2018

class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int maxDiff = 0;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            maxDiff = Math.max(maxDiff, nums[i] - nums[i - 1]);
        }
        return maxDiff;
    }
}