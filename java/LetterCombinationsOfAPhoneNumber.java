/*
 * Given a digit string, return all possible letter combinations that the number could represent.
 */

// Solution 2: Iterative approach
// Process one digit at a time.
// Append all available letters to all previous results.
// Repeat until all digits are processed.
// Time: O(4^n)
// Space: O(1)
// 11/07/2017

class Solution {
    public List<String> letterCombinations(String digits) {
        String[] map = {"", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        res.add("");
        for (int index = 0; index < digits.length(); index++) {  // haven't find all digits
            int len = res.size();
            String letter = map[digits.charAt(index) - '0'];
            for (int i = 0; i < len; i++) {     // for every string in the list
                String last = res.remove();
                for (int j = 0; j < letter.length(); j++) {  // append letters to all strings
                    res.add(last + letter.charAt(j));
                }
            }
        }
        return res;
    }
}

// Solution 1: DFS and backtracking
// First create mapping between digit and characters.
// Then perform DFS to build all possible combinations.
// Time: O(4 ^ n) - each digit can have at most 4 branches, and we have n digits
// Space: O(n) - call stack
// 09/23/2018
class Solution {
    String[] keys = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        search(digits, res, 0, "");
        return res;
    }
    
    private void search(String digits, List<String> res, int index, String cur) {
        if (index == digits.length()) {
            res.add(cur);
            return;
        }
        for (char c : keys[digits.charAt(index) - '0'].toCharArray()) {
            search(digits, res, index + 1, cur + c);
        }
    }
}