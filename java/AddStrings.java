/*
 * Given two non-negative integers num1 and num2 represented as string, 
 * return the sum of num1 and num2.
 * 
 * Note:
 * 
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

public class Solution {
    public String addStrings(String num1, String num2) {
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length()-1, j = num2.length()-1; i >= 0 || j >= 0 || carry == 1; i--, j--) {
            int a = i < 0 ? 0 : (num1.charAt(i) - '0');
            int b = j < 0 ? 0 : (num2.charAt(j) - '0');
            sb.insert(0, (a + b + carry) % 10); 
            carry = (a + b + carry) / 10;
        }
        
        return sb.toString();
    }
}

/*
// convert string to int, do addition, then convert back to string
// OVERFLOAT
public class Solution {
    public String addStrings(String num1, String num2) {
        long num1i = 0;
        long num2i = 0;
        
        for (int i = 0; i < num1.length(); i++) {
            num1i *= 10;
            num1i += num1.charAt(i) - '0';
        }
        
        for (int i = 0; i < num2.length(); i++) {
            num2i *= 10;
            num2i += num2.charAt(i) -'0';
        }
        
        long sumi = num1i + num2i;
        System.out.print(sumi);
        if (sumi == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (sumi != 0) {
            sb.insert(0, (char) (sumi%10 + '0'));
            sumi /= 10;
        }
        
        return sb.toString();
    }
}
*/