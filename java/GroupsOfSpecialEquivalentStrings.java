/*
	You are given an array A of strings.
	Two strings S and T are special-equivalent if after any number of moves, S == T.
	A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].
	Now, a group of special-equivalent strings from A is a non-empty subset of A such that any string not in A is not special-equivalent with any string in A.
	Return the number of groups of special-equivalent strings from A.

	Example 1:
	Input: ["a","b","c","a","c","c"]
	Output: 3
	Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]

	Example 2:
	Input: ["aa","bb","ab","ba"]
	Output: 4
	Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]

	Example 3:
	Input: ["abc","acb","bac","bca","cab","cba"]
	Output: 3
	Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]

	Example 4:
	Input: ["abcd","cdab","adcb","cbad"]
	Output: 1
	Explanation: 1 group ["abcd","cdab","adcb","cbad"]
	 
	Note:
	1 <= A.length <= 1000
	1 <= A[i].length <= 20
	All A[i] have the same length.
	All A[i] consist of only lowercase letters.
 */

// Solution 1:
// For each string, create a special encoding for its characters.
// Each encoding contains 26 characters and their occurrence on odd and even indices.
// For example:
// "aab" -> "0(1,1)2(0,1)"
// Strings with same encoding are in the same group.
//
// Time: O(n)
// Space: O(n)
// 08/25/2018

class Solution {
    public int numSpecialEquivGroups(String[] A) {
        HashSet<String> set = new HashSet<>();
        for (String a : A) {
            int[] odd = new int[26];
            int[] even = new int[26];
            for (int i = 0; i < a.length(); i++) {
                if (i % 2 == 0) {
                    even[a.charAt(i) - 'a']++;
                } else {
                    odd[a.charAt(i) - 'a']++;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append(i + "(" + odd[i] + "," + even[i] + ")");
            }
            String key = sb.toString();
            if (!set.contains(key)) {
                set.add(key);
            }
        }
        return set.size();
    }
}