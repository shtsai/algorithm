/*
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 */

// Solution 2: HashMap
// First find all pairs of numbers and store them in a hashMap
// by using their sum as the index.
// Then similar to 2SUM, find 2 pairs of pairs that adds up to the target.
// Here duplicates are handled by sorting the index list and add to a hashset.
//
// Time: O(n^2)
// Space: O(n^2) - n^2 pairs
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        HashSet<List<Integer>> set = new HashSet<>();
        HashMap<Integer, List<int[]>> pairs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                int[] array = new int[2];       // store the indices of this pair so that we can distiguish duplicates
                array[0] = i;       
                array[1] = j;
                if (!pairs.containsKey(sum)) {
                    pairs.put(sum, new ArrayList<>());
                }
                pairs.get(sum).add(array);
            }
        }
        
        for (Integer x : pairs.keySet()) {
            if (pairs.containsKey(target - x)) {
                List<int[]> xpairs = pairs.get(x);
                List<int[]> ypairs = pairs.get(target - x);
                for (int[] xpair : xpairs) {
                    for (int[] ypair : ypairs) {
                        if (xpair[0] == ypair[0] || xpair[0] == ypair[1] || xpair[1] == ypair[0] || xpair[1] == ypair[1]) {
                            continue;
                        }
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[xpair[0]]);
                        list.add(nums[xpair[1]]);
                        list.add(nums[ypair[0]]);
                        list.add(nums[ypair[1]]);
                        Collections.sort(list);
                        set.add(list);
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        res.addAll(set);
        return res;
    }
}

// Solution 1: Four pointers
// Sort the array in ascending order
// Use two pointers to point at first and second number.
// The remaining two pointers do two sum operations to find target.
// Need to handle duplicate numbers.
// Time: O(n^3) - two outer loop, and one O(n) two sum search
// Space: O(1)
// 10/03/2017

public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;   // skip duplicates
            for (int j = i + 1; j < nums.length -2; j++){  // convert into 3 Sum
                if (j > i + 1 && nums[j] == nums[j-1]) continue;   // skip duplicates
                int k = j + 1;
                int l = nums.length - 1;
                
                while (k < l) {  // convert into 2 Sum
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target){
                        result.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        while(k < l && nums[k]==nums[k+1]) k++;  // skip duplicates
                        while(k < l && nums[l]==nums[l-1]) l--;  // skip duplicates
                        k++;
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        return result;
    }
}