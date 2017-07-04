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