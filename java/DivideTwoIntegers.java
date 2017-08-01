/*
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * If it is overflow, return MAX_INT.
 */

public class Solution {
    public int divide(int dividend, int divisor) {
        // convert dividend and divisor to long to prevent overflow
        // and turn both number into positives
        long dvd = dividend, dvs = divisor;
        int sign = 1;
        if (dvd < 0 && dvs < 0) {
            dvd *= -1;
            dvs *= -1;
        } else if (dvd < 0) {
            dvd *= -1;
            sign = -1;
        } else if (dvs < 0) {
            dvs *= -1;
            sign = -1;
        }
        
        long res = positive_divide(dvd, dvs) * sign;
           
        // check if overflow 
        if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) res;
    }
    
    // helper function that recursively divide two postive numbers
    public long positive_divide(long pdvd, long pdvs) {
        if (pdvd < pdvs) return 0;  //dividend < divisor
        long res = 1; 
        while (pdvd >= pdvs * res * 2) {    // guess result, double by 2 each round
            res *= 2;
        }
        return res + positive_divide(pdvd - pdvs * res, pdvs);
    }
}