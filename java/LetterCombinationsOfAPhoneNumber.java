/*
 * Given a digit string, return all possible letter combinations that the number could represent.
 */

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