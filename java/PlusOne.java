/*
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.
 */

public class Solution {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >= 0; i--) { // start from the right most digit
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // done
            }
            digits[i] = 0; // 9 + 1 = 10, set this digit to 0 and do the carry
        }

        // at this point, we overflow the array, need a larger array here
        int[] newDigits = new int[digits.length+1];
        newDigits[0] = 1; 
        return newDigits;
    }
}