/*
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */

// Solution 1:
// Add two strings from right to left digit by digit.
// Time: O(m + n)
// Space: O(1)
// version 2:
// 11/16/2017
class Solution {
    public String addBinary(String a, String b) {
        int pa = a.length()-1;
        int pb = b.length()-1;
        int carry = 0;
        String res = "";
        while (!(pa == -1 && pb == -1 && carry == 0)) {
            int da = pa >= 0 ? a.charAt(pa--) - '0' : 0;
            int db = pb >= 0 ? b.charAt(pb--) - '0' : 0;
            int sum = da + db + carry;
            carry = sum / 2;
            res = sum % 2 + res;
        }
        return res;
    }
}

// version 1:
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