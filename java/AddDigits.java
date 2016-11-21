/*
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 *
 * For example:
 * Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
 */

public class Solution {
    public int addDigits(int num) {
        if (num <= 0) return num;   // for negative numbers, return themselves
        int result = 0;
        while (num != 0) {
            result += num % 10;   // store each digit in the result
            num /= 10;            // remove added digits
            if (num == 0 && result/10 != 0) {   // when num has no more digits, and the result still have more than one digit
                num = result;       // repeat the above process
                result = 0;
            }
        }
        return result;
    }
}