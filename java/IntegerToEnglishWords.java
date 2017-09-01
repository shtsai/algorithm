/*
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * 
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */

// Solution 2:
// much more consice version
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

// Solution 1:
// process number by units (billion, million, thousand, hundred, etc)
// recursively solve
class Solution {
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        StringBuilder sb = new StringBuilder();
        if (num < 0) {
            sb.append("Negative ");     // store sign
            num *= -1;                  // convert to positive
        }
        if (num >= 1000000000) {
            int billions = num / 1000000000;
            int rest = num % 1000000000;
            String restString = underOneBillion(rest);
            String billionsString = underOneThousand(billions);
            if (billions == 0) sb.append(restString);
            else if (rest == 0) sb.append(billionsString + " Billion");
            else sb.append(billionsString + " Billion " + restString);
        } else {   // num < billion
            sb.append(underOneBillion(num));
        }
        return sb.toString();
    }
    
    private String underOneBillion(int num) {
        int millions = num / 1000000;
        int rest = num % 1000000;
        String restString = underOneMillion(rest);
        String millionsString = underOneThousand(millions);
        if (millions == 0) return restString;
        if (rest == 0) return millionsString + " Million";
        return millionsString + " Million " + restString;
    }
    
    private String underOneMillion(int num) {
        int thousands = num / 1000;
        int rest = num % 1000;
        String restString = underOneThousand(rest);
        String thousandsString = underOneThousand(thousands);
        if (thousands == 0) return restString;
        if (rest == 0) return thousandsString + " Thousand";
        return thousandsString + " Thousand " + restString;
    }
    
    private String underOneThousand(int num) {
        int hundreds = num / 100;
        int rest = num % 100;
        String restString = underOneHundred(rest);
        String hundredsString = underTen(hundreds);
        if (hundreds == 0) return restString;
        if (rest == 0) return hundredsString + " Hundred";
        return hundredsString + " Hundred " + restString;
    }
    
    private String underOneHundred(int num) {
        if (num < 10) return underTen(num);
        else if (num == 10) return "Ten";
        else if (num == 11) return "Eleven";
        else if (num == 12) return "Twelve";
        else if (num == 13) return "Thirteen";
        else if (num == 14) return "Fourteen";
        else if (num == 15) return "Fifteen";
        else if (num == 16) return "Sixteen";
        else if (num == 17) return "Seventeen";
        else if (num == 18) return "Eighteen";
        else if (num == 19) return "Nineteen";
        else {      // >= 20
            int left = num / 10;
            int right = num % 10;
            String space = right == 0 ? "" : " ";
            String rightString = underTen(right);
            if (left == 2) return "Twenty" + space + rightString;
            else if (left == 3) return "Thirty" + space + rightString;
            else if (left == 4) return "Forty" + space + rightString;
            else if (left == 5) return "Fifty" + space + rightString;
            else if (left == 6) return "Sixty" + space + rightString;
            else if (left == 7) return "Seventy" + space + rightString;
            else if (left == 8) return "Eighty" + space + rightString;
            else if (left == 9) return "Ninety" + space + rightString;
        }
        return "";
    }
    
    private String underTen(int num) {
        if (num == 0) return "";
        else if (num == 1) return "One";
        else if (num == 2) return "Two";
        else if (num == 3) return "Three";
        else if (num == 4) return "Four";
        else if (num == 5) return "Five";
        else if (num == 6) return "Six";
        else if (num == 7) return "Seven";
        else if (num == 8) return "Eight";
        else if (num == 9) return "Nine";
        return "";
    }
}