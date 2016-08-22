/**
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */

public class Solution {
    public int romanToInt(String s) {

        // use a map to store the value of corresponding character
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('M',1000);
        map.put('D',500);
        map.put('C',100);
        map.put('L',50);
        map.put('X',10);
        map.put('V',5);
        map.put('I',1);
        
        int length = s.length();
        int result = 0;
        
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            int value = map.get(ch);
            int nextValue;
            
            if (i != length - 1) {
                char nextCh = s.charAt(i+1);
                nextValue = map.get(nextCh);
            } else {
                nextValue = 0;
            }

            // if the value of current char is lower than the next char
            // subtract it from the total value
            // otherwise add it to the result

            if (value < nextValue) {
                result -= value;
            } else {
                result += value;
            }
        }
        
        return result;
    }
}