/*
    Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

    "abc" -> "bcd" -> ... -> "xyz"
    Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

    For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
    A solution is:

    [
      ["abc","bcd","xyz"],
      ["az","ba"],
      ["acef"],
      ["a","z"]
    ]
 */

// Solution 2:
// Alternatively, we can use the sequences of differences between 
// characters as the key to store all strings.
// The whole process is very similar to solution 1
// 10/08/2017

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            StringBuilder sb = new StringBuilder();
            if (s.length() == 1) sb.append("a");
            for (int i = 1; i < s.length(); i++) {
                int shifted = s.charAt(i) - s.charAt(i-1);
                if (shifted < 0) shifted += 26;
                sb.append((char) shifted);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) map.put(key, new ArrayList<String>());
            map.get(key).add(s);
        }
        
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
}

// Solution 1:
// Shift all strings back to their base form, 
// meaning started with letter 'a'.
// We do this by subtracting every char by the first char.
// Note to be careful with the wrap-around case.
// When the difference falls below 0, we need to add 26 to it.
// Time: O(ns) - n:# of words, s:length of each word
// Space: O(ns)
// 10/08/2017

class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            StringBuilder sb = new StringBuilder();
            char base = s.charAt(0);
            for (int i = 0; i < s.length(); i++) {
                int shifted = s.charAt(i) - base;
                if (shifted < 0) shifted += 26;
                sb.append((char) shifted);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) map.put(key, new ArrayList<String>());
            map.get(key).add(s);
        }
        
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }
}