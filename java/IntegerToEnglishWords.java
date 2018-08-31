/*
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * 
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */

// Solution 2: Recursive
// 08/31/2018
public class Solution {
    private final String[] belowTwenty = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        } else {
            return helper(num); 
        }
    }
    
    private String helper(int num) {
        String result = new String();
        if (num < 20) {
            result = belowTwenty[num];
        } else if (num < 100) {
            result = tens[num/10] + " " + helper(num % 10);
        } else if (num < 1000) {
            result = helper(num/100) + " Hundred " +  helper(num % 100);
        } else if (num < 1000000) {
            result = helper(num/1000) + " Thousand " +  helper(num % 1000);
        } else if (num < 1000000000) {
            result = helper(num/1000000) + " Million " +  helper(num % 1000000);
        } else {
            result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
        }
        return result.trim();
    }
}

// Solution 1: Iterative
// reference: https://discuss.leetcode.com/topic/23054/my-clean-java-solution-very-easy-to-understand
class Solution {
    private final String[] under20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens =  {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] thousands =  {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String word = "";
        Boolean negative = false;
        if (num < 0) {
            negative = true;
            num *= -1;
        }
        int i = 0;
        while (num > 0) {
            int toProcess = num % 1000;
            if (toProcess > 0) {
                word = process(toProcess) + thousands[i] + " " + word;
            }
            num /= 1000;
            i++;
        }
        
        word = word.trim();
        if (negative) word = "Negative " + word;
        return word;
    }
    
    private String process(int num) {
        if (num == 0) return "";
        else if (num < 20) return under20[num] + " ";
        else if (num < 100) {
            return tens[num/10] + " " + process(num%10);
        } else {    // >= 100
            return process(num/100) + "Hundred " + process(num%100);
        }
    }
}
