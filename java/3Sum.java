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
// Use a pointer `i` to scan through the array, the value it points at is the target
// Use another two pointers `left` and `right` to search for 2Sum for target value in the remaining array.
// Be careful with duplicates, skip duplicate numbers.
//
// Time: O(n^2) - two nested loops
// Space: O(1)
// 09/07/2018
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int target = -nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    do {
                        left++;
                    } while (left < right && nums[left - 1] == nums[left]);
                    do {
                        right--;
                    } while (left < right && nums[right + 1] == nums[right]);
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
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