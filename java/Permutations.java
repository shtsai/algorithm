/*
 * Given a collection of distinct numbers, return all possible permutations.
 */

// Solution 3:
// iterative approach
// add one integer at a time, and add it to all possible insert position
// time: O(n!): n! permutations
// space O(n * n!): n! permutations, each has length of n
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> list = new ArrayList<Integer>();
        list.add(nums[0]);
        res.add(list);
        for (int i = 1; i < nums.length; i++) {     // for every number
            List<List<Integer>> newRes = new ArrayList<>();
            for(int j = 0; j <= i; j++) { // for every insert position
                for (List<Integer> l : res) {
                    List<Integer> newL = new ArrayList<Integer>(l);
                    newL.add(j, nums[i]);
                    newRes.add(newL);
                }
            }
            res = newRes;
        }
        return res;
    }
}


 // Solution 2:
 // first convert int[] to ArrayList so that we can add and remove easily
 // better performance than solution 1, yet use extra memory space
 class Solution {
     public List<List<Integer>> permute(int[] nums) {
         List<List<Integer>> res = new ArrayList<>();
         if (nums == null || nums.length == 0) return res;
         ArrayList<Integer> number = new ArrayList<>();      // convert int[] to arraylist so that we can add and remove
         for (int n : nums) number.add(n);
         helper(res, number, nums.length, new ArrayList<Integer>());
         return res;
     }

     public void helper(List<List<Integer>> res, ArrayList<Integer> nums, int len, ArrayList<Integer> list) {
         if (list.size() == len)  res.add(new ArrayList<Integer>(list));   // reach end

         for (int i = 0; i < nums.size(); i++) {     // try number at different position
             int n = nums.get(i);
             list.add(n);
             nums.remove(i);  // remove this number so that we won't use it again later
             helper(res, nums, len, list);   // recursively solve subproblems
             nums.add(i, n);  // add it back so that we can use it in the next iteration
             list.remove(list.size()-1);     // remove the last int we add to the list
         }
     }
 }

// Solution 1:
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<Integer>(), nums);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));   // need this
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {   // skip numbers that we already used
                continue;                   // need to check for every integer we are going to add
            }                               // influence performance O(len(list.length)) time
            list.add(nums[i]);
            helper(result, list, nums);
            list.remove(list.size()-1);
        }
    }
}
