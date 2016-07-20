/**
 * Determine whether an integer is a palindrome. 
 */


public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        
        int[] digits = new int[20];
        int length = 0;
        
        while (x != 0) {
            digits[length] = x % 10;
            x = x / 10;
            length += 1;
        }
        
        for (int i=0; i< (length+1)/2; i++) {
            System.out.println(digits[i]);
            System.out.println(digits[length-1-i]);
            if (digits[i] != digits[length-1-i]) {
                return false;
            }
            
        }
        
        return true;
    }
}