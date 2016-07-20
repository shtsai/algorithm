/**
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 */


public class Solution {
    public int reverse(int x) {
	   int input = x;
       int[] digit = new int[100];
       int digits = 0;
       int result = 0;
	        
       while (input != 0) {
           int last = input % 10;
	       //System.out.println(last);
	        
	       input = input / 10;
           digit[digits] = last;
           digits += 1;
	   };
	        
	        
       for (int i=0; i<digits; i++){
           result += digit[i] * Math.pow(10,digits-i-1);
           if (result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE) {
		      	return 0;
		   };
	   }
	        
       return result;
    }
}