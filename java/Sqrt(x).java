/*
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x.
 */

// Binary search
public class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;   // corner case
        int left = 1;           // start left from to prevent mid to be 0, which can cause divide by zero problem
        int right = x;          // the sqrt of x is at most x
        
        while (true) {
            int mid = left + (right - left) / 2;  // prevent overflow
            if (mid > x / mid) {    // mid is too big
                right = mid - 1;
            } else {  // (mid * mid <= x)   // mid is too small
                if ((mid + 1) > x / (mid + 1)) {    // but mid+1 is also too big, so mid is the right answer
                    return mid;
                }
                left = mid + 1;
            }
        }
    }
}