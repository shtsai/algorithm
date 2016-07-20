/**
 * Given two strings s and t, determine if they are isomorphic.
 */


public class Solution {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        
        int length = s.length();
        if (length != t.length()) {
            return false;
        }
        
        for (int i = 0; i < length; i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            
            if (!map.containsKey(sc)) {
                if (!map.containsValue(tc)) {
                    map.put(sc,tc);
                } else {
                    // the value exists in the map but is associated with some other key
                    // therefore, it is not isomorphic
                    return false;
                }
            } else {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
}