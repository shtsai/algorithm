/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return
 * 
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */

// Solution 1: Recursion + DFS
// Time: O(n^2)
// Space: O(n)
// version 2:
// 01/27/2018
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        helper(s, res, 0, new ArrayList<String>());
        return res;
    }
    
    public void helper(String s, List<List<String>> res, int index, List<String> current) {        
        if (index == s.length()) {
            res.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = index+1; i <= s.length(); i++) {
            String curString = s.substring(index, i);
            if (isPalindrome(curString)) {
                current.add(curString);
                helper(s, res, i, current);
                current.remove(current.size() - 1);
            }
        }
    }
    
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}


// version 1:
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        helper(s, result, new ArrayList<String>(), 0);
        return result;
    }
    private void helper(String s, List<List<String>> result, List<String> list, int start) {
        if (start == s.length() && list.size() > 0) {   // base case
            result.add(new ArrayList<String>(list));
            return;
        }
        
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) { 
                list.add(s.substring(start, end+1));
                helper(s, result, list, end+1); // recursively solve subproblem
                list.remove(list.size()-1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int start, int end) {
        if (end - start == 0) return true;   // one-char string
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
}