/*
 * Given an array of strings, group anagrams together.
 * 
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
 * Return:
 * 
 * [
 *   ["ate", "eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 */

// Solution 1:
// use int[] to get character frequency
// Then convert int array into a string, which uniquely identify
// a set of anagrams.
// Good when words are long
// Time: O(26nk) = O(nk) - n is the number of strings, k is the length of string
// Space: O(nk) - HashMap
// 09/23/2018
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }
            String key = Arrays.toString(freq);
            map.computeIfAbsent(key, x -> new ArrayList<>()).add(s);
        }
        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}

// Solution 2: sort strings by character
// convert string to char[], then sort char[] and convert it back to string form
// all anagrams will have the same key
// Better when words are short.
// Time: O(nklogk) - n iterations, sort each string is klogk
// Space: O(n * k) - HashMap
// 09/23/2018
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.computeIfAbsent(sorted, x -> new ArrayList<>()).add(s);
        }
        for (List<String> list : map.values()) {
            res.add(list);
        }
        return res;
    }
}

