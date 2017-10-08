/*
	Numbers can be regarded as product of its factors. For example,

	8 = 2 x 2 x 2;
	  = 2 x 4.
	Write a function that takes an integer n and return all possible combinations of its factors.

	Note: 
	You may assume that n is always positive.
	Factors should be greater than 1 and less than n.
	Examples: 
	input: 1
	output: 
	[]
	input: 37
	output: 
	[]
	input: 12
	output:
	[
	  [2, 6],
	  [2, 2, 3],
	  [3, 4]
	]
	input: 32
	output:
	[
	  [2, 16],
	  [2, 2, 8],
	  [2, 2, 2, 4],
	  [2, 2, 2, 2, 2],
	  [2, 4, 4],
	  [4, 8]
	]
 */

// Solution 1: DFS, recursive search
// Use a helper function to recursively search.
// Need to be careful with start value, can only start with a 
// larger or same value as the previous recurrence to avoid duplicates.
// When we have reached n == 1 and the list contains more than
// one numbers, we have reached a result and add it to result list.
// Time: O(n^2)
// Space: O(n) - at most (n/2) recursive calls in one branch
// reference: https://discuss.leetcode.com/topic/21082/my-recursive-dfs-java-solution
// 10/08/2017

class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 3) return res;
        helper(res, new ArrayList<Integer>(), n, 2);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list,int n, int start) {
        if (n <= 1) {
            if (list.size() > 1) {
                res.add(new ArrayList<Integer>(list));
                return;
            }
        }
        
        for (int i = start; i <= n; i++) {
            if (n % i != 0) continue;
            list.add(i);
            helper(res, list, n/i, i);  // only allow to continue searching with a larger start value
            list.remove(list.size()-1); // to avoid duplicates
        }
    }
}