/**
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 */

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        // binary search
        int start = 1;
        int end = n;
        
        while (start < end) {
            int myGuess = start + (end - start)/2;
            int result = guess(myGuess);
            
            if (result == 0) {
                return myGuess;
            }
            if (result == 1) {
                start = myGuess+1;
            } else {
                end = myGuess-1;
            }
            
        }
        return start;
    
    }
}