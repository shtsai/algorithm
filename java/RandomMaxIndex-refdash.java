/*
    ** Refdash **
    Find the maximum value in an array, return its index.
    If there are multiple maximum, randomly return the index of one of them.

    [1, 3, 2, 2, 3, 2, 3]
        ^        ^     ^
        |         \     \___ 1/3
        |          \
    (1*1/2*2/3)  (1/2*1/3)

    P(1st) = 1 * 1/2 * 2/3 = 1/3
    P(2nd) = 1/2 * 2/3 = 1/3
    P(3rd) = 1/3 

    max = 3
    count = 3
    1/3 to swap 
    maxIndex = 1
*/

// Reservior sampling
// use conditional probability
// time: O(n) -- one loop
// space: O(1) -- no data structure used
public int randomPickMax(int[] nums) {
    Random rand = new Random();
    int max = Integer.MIN_VALUE;    
    int maxIndex = -1;              // stores the index of max that we are going to return
    int count = 0;                  // count the number max we have seen
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > max) {
            max = nums[i];
            maxIndex = i;
            count = 1;
        } else if (nums[i] == max) {    // find a another max value
            count++;
            if (rand.nextDouble(1) < (1/count)) {  // swap with (1/count) probability
                maxIndex = i;
            }
        }
    }
    return maxIndex;
}