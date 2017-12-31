/*
 	Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 	Find all unique triplets in the array which gives the sum of zero.
 */

// Solution 3: convert to two sum, hashset approach
// Sort the array in acsending order
// Use a pointer to scan through the array, the value it points at is the target
// Use hashset approach to search for 2Sum for that value in the remaining array.
// add all sorted intermediate result to a hashset to handle duplicates.
//
// Time: O(n^2) - can be less efficient b/c we didn't skip duplicates
// Space: O(n) - HashSet

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (set.contains(-nums[i] - nums[j])) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(-nums[i]-nums[j]);
                    Collections.sort(l);
                    res.add(l);
                } else {
                    set.add(nums[j]);
                }
            }
        }
        List<List<Integer>> reslist = new ArrayList<>();
        reslist.addAll(res);
        return reslist;
    }
}

// Solution 2: convert it to two sum, two pointer search
// Sort the array in acsending order
// Use a pointer to scan through the array, the value it points at is the target
// Use another two pointers to search for 2Sum for that value in the remaining array.
// Be careful with duplicates, skip duplicate numbers.
//
// Time: O(n^2) - two nested loops
// Space: O(1)

// version 2
// 12/30/2017
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length-2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int j = i+1, k = nums.length-1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    l.add(nums[j]);
                    l.add(nums[k]);
                    res.add(l);
                    while (j < k && nums[j+1] == nums[j]) j++;
                    while (j < k && nums[k-1] == nums[k]) k--;
                    j++;
                    k--;
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}

// Version 1
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