/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

// DP solution
public class Solution {
    public int minCut(String s) {
        int[] cut = new int[s.length()];
        char[] chars = s.toCharArray();
        boolean[][] pal = new boolean[chars.length][chars.length];
        
        for (int end = 0; end < chars.length; end++) {
            int min = end;      // worst case, cut between every char
            for (int start = 0; start <= end; start++) {
                if (chars[start] == chars[end] && (end - start < 2 || pal[start+1][end-1])) {
                    if (start == 0) {   // s[start, end] is a palindrome, no cut needded
                        min = 0;
                    } else {
                        min = Math.min(min, 1 + cut[start - 1]);   // dp case: 1 cut plus the result to previous subproblem
                    }
                    pal[start][end] = true;
                }
            }
            cut[end] = min;
        }
        
        return cut[chars.length-1];
        
    }
}

// brute force
// find all possible partitions, and find the min number of cuts
// time limit exceeded
public class Solution {
    int min = Integer.MAX_VALUE;
    public int minCut(String s) {
        if (s == null || s.length() == 0 || isPalindrome(s, 0, s.length()-1)) return 0;
        helper(s, new ArrayList<String>(), 0);
        return min;
    }
    
    private void helper(String s, List<String> list, int start) {
        if (start == s.length()) min = Math.min(min, list.size()-1);
        
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                list.add(s.substring(start, end+1));
                helper(s, list, end+1);
                list.remove(list.size()-1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int start, int end) {
        if (start == end) return true;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
}