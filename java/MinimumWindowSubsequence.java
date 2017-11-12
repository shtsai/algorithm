/*
	Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.

	If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.

	Example 1:
	Input: 
	S = "abcdebdde", T = "bde"
	Output: "bcde"
	Explanation: 
	"bcde" is the answer because it occurs before "bdde" which has the same length.
	"deb" is not a smaller window because the elements of T in the window must occur in order.
	Note:

	All the strings in the input will only contain lowercase letters.
	The length of S will be in the range [1, 20000].
	The length of T will be in the range [1, 100]. 
 */

// Solution 1:
// Similar to Minimum Windows String.
// But we need to perform another check to see if the sequence is valid.
//
// Time: O(n)
// Space: O(1)
// 11/11/2017

class Solution {
    public String minWindow(String S, String T) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : T.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        int count = map.size();
        int min = Integer.MAX_VALUE, min_start = -1;
        int left = 0, right = 0;
        boolean found = false;
        while (right < S.length()) {
            char c = S.charAt(right);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if (map.get(c) == 0) {
                    count--;
                }
            }
            right++;
            if (count == 0 && isValid(S.substring(left, right), T)) {
                found = true;
            }
            while (count == 0 && found && left < right) {
                if (right-left < min) {
                    if (isValid(S.substring(left, right), T)) {
                        min = right - left;
                        min_start = left;
                    } else {
                        found = false;
                    }
                }
                char lc = S.charAt(left);
                if (map.containsKey(lc)) {
                    map.put(lc, map.get(lc) + 1);
                    if (map.get(lc) > 0) {
                        count++;
                    }
                }
                left++;
            }
        }
        if (min_start == -1) return "";
        return S.substring(min_start, min_start + min);
    }
    
    public boolean isValid(String a, String b) {
        int i = 0, j = 0;
        while (j < b.length() && i < a.length()) {
            while (i < a.length() && a.charAt(i) != b.charAt(j)) {
                i++;
            }
            if (i >= a.length()) break;
            i++;
            j++;
        }
        return j == b.length();
    }
}