/*
 * Shuffle a set of numbers without duplicates.
 *
 * Example:
 *
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 *
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 *
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * 
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 */
 
public class Solution {

    private int[] original;
    private int[] shuffled;
    private Random rand;
    
    public Solution(int[] nums) {
        rand = new Random();
        shuffled = nums;
        original = new int[nums.length];         // maintain a copy of the original array
        for (int i = 0; i < nums.length; i++) {  // original array never gets modified, only shuffled array changes
            original[i] = shuffled[i];
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for (int i = 0; i < original.length; i++) {     // restore the original array
            shuffled[i] = original[i];
        }
        return shuffled;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        
        for (int i = 0; i < original.length; i++) {
            int j = rand.nextInt(i+1);  // <- key step in shuffling
            int temp = shuffled[i];
            shuffled[i] = shuffled[j];
            shuffled[j] = temp;
        }
        return shuffled;
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */