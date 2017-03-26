/*
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */

public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        int carry = 0;
        while (indexA >= 0 || indexB >= 0) {
            int sum = carry;
            if (indexA >= 0) {
                sum += a.charAt(indexA) - '0';
                indexA--;
            }
            if (indexB >= 0) {
                sum += b.charAt(indexB) - '0';
                indexB--;
            }
            sb.insert(0, sum % 2);
            carry = sum / 2;
        }
        
        if (carry != 0) {
            sb.insert(0, carry);
        }
        
        return sb.toString();
    }
}