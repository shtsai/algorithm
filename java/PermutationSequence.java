/*
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 */

// Solution 2:
// Do not calculate permutations on by one.
// Since the input array is sorted, we can calculate to know which number
// the kth permutation sequence starts with.
// Then we can use the same approach to recursively solve the problem.
//
// https://discuss.leetcode.com/topic/17348/explain-like-i-m-five-java-solution-in-o-n/2

// version 2: 
// calculate factorials in advance
class Solution {
    public String getPermutation(int n, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) nums.add(i);   // add numbers to an arraylist for easy access and removal
        StringBuilder sb = new StringBuilder();
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for (int i = 1; i < factorial.length; i++) {   // build an factorial array, for faster access
            factorial[i] = factorial[i-1] * i;
        }
        
        k--;        // change k to zero-indexed
        while (!(nums.size() == 0)) {
            int nextPermutation = factorial[n-1];
            int choose = k / nextPermutation;
            sb.append(nums.get(choose));
            nums.remove(choose);
            k %= nextPermutation;
            n--;
        }
        
        return sb.toString();
    }
}

// Solution 2: version 1
class Solution {
    public String getPermutation(int n, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) nums.add(i);   // add numbers to an arraylist for easy access and removal
        StringBuilder sb = new StringBuilder();
        k--;        // change k to zero-indexed
        while (!(nums.size() == 0)) {
            int n2 = n - 1;
            int nextPermutation = 1;
            while (n2 > 0) {                
                nextPermutation *= n2;      // get the number of permutation for (n-1) level
                n2--;
            }
            int choose = k / nextPermutation;
            sb.append(nums.get(choose));
            nums.remove(choose);
            k %= nextPermutation;
            n--;
        }
        
        return sb.toString();
    }
}



// Solution 1:
// standard backtracking approach
// compute permutations one by one, until reach kth permutation
// Time limit exceeded

class Solution {
    int count;
    String res;
    public String getPermutation(int n, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) nums.add(i);
        count = 0;
        res = "";
        helper(nums, k, n, new StringBuilder());
        return res;
    }
    
    private void helper(ArrayList<Integer> nums, int k, int total, StringBuilder sb) {
        if (sb.length() == total) {
            count++;
            if (count == k) res = sb.toString();
            return;
        }
        
        for (int i = 0; i < nums.size(); i++) {
            int n = nums.get(i);
            sb.append(n);
            nums.remove(i);
            helper(nums, k, total, sb);
            nums.add(i, n);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}