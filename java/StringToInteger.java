/*
 * Implement atoi to convert a string to an integer.
 */

 public class Solution {
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        Queue<Integer> q = new LinkedList<>();  // uses a linked list to store valid numbers
        int sign = 1;   

        str = str.trim(); 	// remove white spaces at two ends
        int i = 0;
        if (str.charAt(i) == '+') {   // check for sign
            sign = 1;
            i++;
        } else if (str.charAt(i) == '-') {
            sign = -1;
            i++;
        }
        
        for (; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {  // store valid numbers
                q.add(c - '0');
                number_found = true;
            } else {
                break;
            }
        }
        
        int len = q.size();
        long result = 0;	// use long to store result to prevent overfloat
        for (int j = len - 1; j >= 0; j--) {
            result += q.remove() * (int) Math.pow(10, j);   // calculate the value of the number
        }
        result *= sign;
        if (result > 0 && result >= Integer.MAX_VALUE) {    // check if we exceeds the max and min 
            return Integer.MAX_VALUE;
        }
        if (result <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        
        return (int) result;  // remember to cast to int before return
    }
}