/*
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 */

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
			int target = 0 - nums[i];
			int left = i + 1;
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

		/*
		// brute force (need to take care of duplicates)
		List<List<Integer>> result = new ArrayList<>();

		int len = nums.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				for (int k = j + 1; k < len; k++) {
					if (nums[i] + nums[j] + nums[k] == 0) {
						result.add(Arrays.asList(new int[]{nums[i],nums[j],nums[k]}));
					}
				}
			}
		}
		return result;
		*/
	}
}