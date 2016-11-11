/*
 * Given a string containing only digits, 
 * restore it by returning all possible valid IP address combinations.
 *
 * For example:
 *   Given "25525511135",
 *   return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> result = new ArrayList<>();
        if (s == null || s.length() <= 3 || s.length() > 12) { return result;}   // invalid length
        
        int[] position = new int[] {0, 0, 0, 0};    // each element represents the position of the dot
        
        for (int i = 1; i < 4; i++) {    // the first dot has 3 possible position
            Long value = Long.parseLong(s.substring(0, i));    // check if the value is valid, use Long to avoid overflow
            if (value >= 0 && value <= 255 && value.toString().length() == i) {  // if value is valid and the length of value does not change (aka no 0 in the leftmost position)
                position[0] = i;                     // update position
                helper(result, s, position, 1);      // make recursion call
                position[0] = 0;                     // restore position
            }
        }
        return result;
    }
    
    public void helper(List<String> result, String s, int[] position, int count) {
        if (count == 3) {   // Base case: find all four dot position
            Long lastValue = Long.parseLong(s.substring(position[count-1], s.length()));  // check if last value is valid
            if (lastValue >= 0 && lastValue <= 255 && lastValue.toString().length() == s.length()-position[count-1]) {  // "forth dot" is at the end of the string, valid placement
                String address = buildAddress(s, position);
                result.add(address);    
            }
            return;
        }
        
        if (position[count-1] >= s.length()) {   // invalid position
            return;
        }
        
        int availableChoice = Math.min(4, s.length()-position[count-1]);   // the number of possible choices
        for (int i = 1; i < availableChoice; i++) {
            Long value = Long.parseLong(s.substring(position[count-1], position[count-1]+i));
            if (value >= 0 && value <= 255 && value.toString().length() == i) {
                position[count] = i + position[count-1];   // update position
                helper(result, s, position, count+1);
                position[count] = 0;                     // restore position
            }
        }
    }
    
    public String buildAddress(String s, int[] position) {
        // build the Address string based on the position array
        StringBuilder sb = new StringBuilder();
        int start = 0;
        for (int i = 0; i < 3; i++) {
            sb.append(s.substring(start, position[i]));
            sb.append(".");
            start = position[i];
        }
        sb.append(s.substring(start, s.length()));
        return sb.toString();
    }
}