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
        if (digits == null || digits.length() == 0) return res;
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
// Time: O(4^n) - each digit can have at most 4 branches, and we have n digits
// Space: O(n) - call stack

// version 2: Use String array to store mapping
// 11/07/2017
class Solution {
    public List<String> letterCombinations(String digits) {
        String[] map = {"", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        List<String> res = new ArrayList<>();
        builder(digits, map, res, 0, "");
        return res;
    }
    
    public void builder(String digits, String[] map, List<String> res, int start, String cur) {
        if (start == digits.length()) { // base case
            if (cur.length() != 0) {
                res.add(cur);
            }
            return;
        }
        String next = map[digits.charAt(start)-'0'];
        for (int i = 0; i < next.length(); i++) {
            builder(digits, map, res, start+1, cur + next.charAt(i));
        }
    }
}

// version 1:
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        
        if (digits == null || digits.equals("")) {
            return list;
        }
        
        // create mapping between digits and characters
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('1', new char[] {});
        map.put('2', new char[] {'a','b','c'});
        map.put('3', new char[] {'d','e','f'});
        map.put('4', new char[] {'g','h','i'});
        map.put('5', new char[] {'j','k','l'});
        map.put('6', new char[] {'m','n','o'});
        map.put('7', new char[] {'p','q','r','s'});
        map.put('8', new char[] {'t','u','v'});
        map.put('9', new char[] {'w','x','y','z'});
        
        StringBuilder sb = new StringBuilder();
        helper(digits, list, map, sb);
        
        return list;
    }
    
    private void helper(String digits, List<String> list, HashMap<Character, char[]> map, StringBuilder sb){
        if (sb.length() == digits.length()) {   // valid combination, add to list
            list.add(sb.toString());
            return;
        }
        
        for (char c : map.get(digits.charAt(sb.length()))) {   // sb.length() is the index of the corresponding digits, get its char arrays
            sb.append(c);       // append to the end
            helper(digits, list, map, sb);  // recursive call
            sb.deleteCharAt(sb.length() - 1);  // remove the last char
        }
    }
}