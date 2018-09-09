/*
	Given a string, find the length of the longest substring T that contains at most k distinct characters.

	Example 1:
	Input: s = "eceba", k = 2
	Output: 3
	Explanation: T is "ece" which its length is 3.

	Example 2:
	Input: s = "aa", k = 1
	Output: 2
	Explanation: T is "aa" which its length is 2.
 */

// Solution 1: Sliding window
// Two points left and right
// Time: O(n)
// Space: O(n)
// 09/09/2018

class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) {
            return 0;
        }
        int left = 0, right = 0, count = 0;
        int max = 0;
        HashMap<Character, Integer> current = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            if (!current.containsKey(c)) {
                count++;
            }
            current.put(c, current.getOrDefault(c, 0) + 1);
            right++;
            
            while (left < right && count > k) {
                char lc = s.charAt(left);
                current.put(lc, current.get(lc) - 1);
                if (current.get(lc) == 0) {
                    count--;
                    current.remove(lc);
                }
                left++;
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}