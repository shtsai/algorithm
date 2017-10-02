/*
 	Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 	Find all unique triplets in the array which gives the sum of zero.
 */

// Solution 1: convert it to two sum, two pointer search
// Sort the array in acsending order
// Use a pointer to scan through the array, the value it points at is the target
// Use another two pointers to search for 2Sum for that value in the remaining array.
// Be careful with duplicates, skip duplicate numbers.
//
// Time: O(n^2) - two nested loops
// Space: O(1)
// 10/02/2017
public class Solution {
	public List<List<Integer>> threeSum(int[] nums) {		
		// convert the problem to 2Sum
		List<List<Integer>> result = new ArrayList<>();
		int len = nums.length;
		if (len < 3) {
			return result;
		}
		Arrays.sort(nums);

		for (int i = 0; i < len; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) { // find duplicate and skip
				continue;
			}
			int target = 0 - nums[i];	// use i to point at the target value
			int left = i + 1;			// use two pointers left and right to find two sum
			int right = len - 1;
			while (left < right) {
				if (nums[left] + nums[right] == target) {
					result.add(Arrays.asList(nums[i], nums[left], nums[right]));
					while(left<right && nums[left]==nums[left+1]) left++;	 // find duplicate and skip
					while(left<right && nums[right]==nums[right-1]) right--; // find duplicate and skip
					left++;
					right--;
				} else if (nums[left] + nums[right]  > target) {
					right--;
				} else {
					left++;
				}
			}
		}

		return result;
	}
}

// Solution 1: brute force
// First sort the array in acsending order.
// Then use three nested loop  to try all combinations.
// Be careful with duplicates.
// Time: O(n^3) - Time limit exceeded
// Space: O(1)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
		int len = nums.length;
		for (int i = 0; i < len; i++) {
            while (i > 0 && nums[i] == nums[i-1]) continue;
			for (int j = i + 1; j < len; j++) {
                while (j > i+1 && nums[j] == nums[j-1]) continue;
				for (int k = j + 1; k < len; k++) {
                    while (k > j+1 && nums[j] == nums[j-1]) continue;
					if (nums[i] + nums[j] + nums[k] == 0) {
						result.add(Arrays.asList(nums[i],nums[j],nums[k]));
					}
				}
			}
		}
		return result;
    }
}