/*
    Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.

    QWERTYUIOP
    ASDFGHJKL
    ZXCVBNM

    Example 1:
    Input: ["Hello", "Alaska", "Dad", "Peace"]
    Output: ["Alaska", "Dad"]
    Note:
    You may use one character in the keyboard more than once.
    You may assume the input string will only contain letters of alphabet.
 */

// Solution 2: HashMap
// Time: O(n) - n:total # of characters
// Space: O(1) - constant space for characters
// 07/16/2018
class Solution {
    public String[] findWords(String[] words) {
        String[] rows = new String[] {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        ArrayList<String> res = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < rows.length; i++) {
            for (char c : rows[i].toLowerCase().toCharArray()) {
                map.put(c, i);
            }
        }
        for (String word : words) {
            if (word == "") {
                continue;
            }
            String lowerW = word.toLowerCase();
            int row = map.get(lowerW.charAt(0));
            for (char c : lowerW.toCharArray()) {
                if (map.get(c) != row) {
                    row = -1;
                    break;
                }
            }
            if (row != -1) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}

// Solution 1: HashSet
// Time: O(n) - n:total # of characters
// Space: O(1) - constant space for characters
// 07/16/2018
class Solution {
    public String[] findWords(String[] words) {
        ArrayList<String> res = new ArrayList<>();
        HashSet<Character>[] sets = new HashSet[3];
        sets[0] = new HashSet<Character>();
        sets[1] = new HashSet<Character>();
        sets[2] = new HashSet<Character>();
        for (char c : "qwertyuiop".toCharArray()) {
            sets[0].add(c);
        }
        for (char c : "asdfghjkl".toCharArray()) {
            sets[1].add(c);
        }
        for (char c : "zxcvbnm".toCharArray()) {
            sets[2].add(c);
        }
        for (String word : words) {
            String lowerW = word.toLowerCase();
            int set = -1;
            for (char c : lowerW.toCharArray()) {
                if (set == -1) {
                    if (sets[0].contains(c)) {
                        set = 0;
                    } else if (sets[1].contains(c)) {
                        set = 1;
                    } else {
                        set = 2;
                    }
                } else if (!sets[set].contains(c)) {
                    set = -1;
                    break;
                }
            }
            if (set != -1) {
                res.add(word);
            }
        }
        return res.toArray(new String[0]);
    }
}