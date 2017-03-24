/*
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 */

/*
 * This is a math problem.
 * 1 = 1
 * 4 = 1 + 3
 * 9 = 1 + 3 + 5
 * 16 = 1 + 3 + 5 + 7
 * 25 = 1 + 3 + 5 + 7 + 9
 * 36 = 1 + 3 + 5 + 7 + 9 + 11
 * ....
 * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
 */
 
public class Solution {
    public boolean isPerfectSquare(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        
        return num == 0;
    }
}