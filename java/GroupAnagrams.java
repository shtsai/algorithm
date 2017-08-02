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

// Solution 2:
// convert string to char[], then sort char[] and convert it back to string form
// all anagrams will be the same
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String index = Arrays.toString(chars);
            if (!map.containsKey(index)) map.put(index, new ArrayList<>());
            map.get(index).add(s);
        }
        
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
}

// Solution 1:
// use int[] to get character frequency
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        
        for (String s : strs) {
            int[] freq = new int[26];
            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i)-'a']++;
            }
            // convert the frequency to a string, which is used as the key into the hashmap
            String index = Arrays.toString(freq);
            if (!map.containsKey(index)) map.put(index, new ArrayList<>());
            map.get(index).add(s);
        }
        
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
}