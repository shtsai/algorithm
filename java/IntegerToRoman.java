/*
	Given an integer, convert it to a roman numeral.

	Input is guaranteed to be within the range from 1 to 3999.
 */

// Solution 2: Store key values
// Time: O(1) - constant number of iterations
// Space: O(1) - constant space
// 01/09/2018
class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] units = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int u = 0; u < units.length; u++) {
            if (num >= units[u]) {
                int count = num / units[u];
                for (int i = 0; i < count; i++) {
                    sb.append(roman[u]);
                }
                num %= units[u];
            }
        }

        return sb.toString();
    }
}

// Solution 1: List all possible values
// This solution is efficient b/c there are fixed number of cases.
// Time: O(1)
// Space: O(1) - constant space
class Solution {
    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}