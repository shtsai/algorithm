/*
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
 * 
 * Note:
 * 
 *     The length of both num1 and num2 is < 110.
 *     Both num1 and num2 contains only digits 0-9.
 *     Both num1 and num2 does not contain any leading zero.
 *     You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 */

// process two strings character by character
// store results in a big int array, then build string from there
// reference: https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        if (m == 0 || n == 0) return "";
        int[] res = new int[m+n];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int product = (num1.charAt(i)-'0') * (num2.charAt(j)-'0');    // multiply
                int toAdd = product + res[i+j+1]; // need to add what was already there
                res[i+j+1] = toAdd % 10;
                res[i+j] += toAdd / 10;      //  <-- note the "+" here
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < m+n && res[i] == 0) i++;    // skip all the leading zeros
        for (; i < m+n; i++) sb.append(res[i]);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}