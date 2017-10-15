/*
	Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

	Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

	The order of output does not matter.

	Example 1:

	Input:
	s: "cbaebabacd" p: "abc"

	Output:
	[0, 6]

	Explanation:
	The substring with start index = 0 is "cba", which is an anagram of "abc".
	The substring with start index = 6 is "bac", which is an anagram of "abc".
	Example 2:

	Input:
	s: "abab" p: "ab"

	Output:
	[0, 1, 2]

	Explanation:
	The substring with start index = 0 is "ab", which is an anagram of "ab".
	The substring with start index = 1 is "ba", which is an anagram of "ab".
	The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

// Solution 2: Two pointers
// Very similar to minimum window substring
// First use a hashmap to store character frequency
// Then use pointers to create a window.
// Expand the window until we have found all characters needed,
// then invalidate the window by shifting the start pointer and 
// continue searching.
// Time: O(m) - scan s with two pointers
// Space: O(n) - create hashmap for p
// reference: https://discuss.leetcode.com/topic/68976/sliding-window-algorithm-template-to-solve-all-the-leetcode-substring-search-problem
// 10/15/2017

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int len = p.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (!map.containsKey(p.charAt(i))) {
                map.put(p.charAt(i), 1);
            } else {
                map.put(p.charAt(i), map.get(p.charAt(i))+1);
            }
        }
        
        int count = map.keySet().size();
        int start = 0, end = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);
                if (map.get(c) == 0) count--;

                while (count == 0) {
                    char cstart = s.charAt(start);
                    if (map.containsKey(cstart)) {
                        map.put(cstart, map.get(cstart) + 1);
                        if (map.get(cstart) == 1) {
                            count++;
                            if (end - start + 1 == len) {
                                res.add(start);
                            }
                        }
                    }
                    start++;
                }
            }
            end++;
        }
        return res;
    }
}

// Solution 1:
// Use a HashSet for quick look up.
// First get the char frequency of p, and convert its frequency array into a string.
// Then scan through string s, perform the same operation for possible windows
// and compare their to p's string.
// Time: O(mn) - check all m windows for s, each window of size n
// Space: O(n) - HashMap
// 10/15/2017

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int len = p.length();
        HashSet<Character> set = new HashSet<>();
        int[] codeArray = new int[26];
        for (int i = 0; i < len; i++) {
            codeArray[p.charAt(i)-'a']++;
            set.add(p.charAt(i));
        }
        String key = Arrays.toString(codeArray);
        
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                int[] array = new int[26];
                for (int j = i; j < Math.min(i+len, s.length()); j++) {
                    array[s.charAt(j)-'a']++;
                }
                if (key.equals(Arrays.toString(array))) {
                    res.add(i);
                }
            }
        }
        return res;
    }
}