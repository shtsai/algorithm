/*
 * Given an array, return the max sum possible.
 * If a number n is chosen, then all occurence of n-1 and n+1 are deleted.
 */

class MaximumSumWithNoAdjacentNumber {
    // Solution 1: Two DP arrays
    // At each index we have two choices:
    //	    1: to include the current number
    //	    2: to exclude the current number
    // We can store the results of two choices in two dp arrays.
    // The way we update the dp arrays also depends on the value of current number:
    //	    1: if the number is the same as the previous number,
    //	       include: we can include the current number again
    //	       exclude: we need to exclude the number, so copy previous result
    //	    2: if the number is a consecutive number of the previous one
    //	       include: if we include this number, we must exclude previous one
    //	       exclude: if we exclude this number, just copy the better result from previous step
    //	    3: if the nubmers are not consecutive
    //	       include: choose a better result from the previous step and add to it
    //	       exclude: choose a better result from the previous step
    //
    // Time: O(n) - one pass
    // Space: O(n) - two DP arrays
    // 11/06/2017
    //
    public static int getMaxSum(int[] array) {
	if (array == null || array.length == 0) return 0;
	int[] include = new int[array.length];
	int[] exclude = new int[array.length];
	include[0] = array[0];

	for (int i = 1; i < array.length; i++) {
	    if (array[i] == array[i-1]) {   // two numbers are the same
		include[i] = include[i-1] + array[i];  // we can continue including this number
		exclude[i] = exclude[i-1];  // continue excluding the current number
	    } else if (array[i] - array[i-1] == 1) { // consecutive number
		include[i] = exclude[i-1] + array[i]; 
		exclude[i] = Math.max(include[i-1], exclude[i-1]);
	    } else {  // difference is greater than 1
		include[i] = Math.max(include[i-1], exclude[i-1]) + array[i];
		exclude[i] = Math.max(include[i-1], exclude[i-1]);
	    }
	}

	return Math.max(include[array.length-1], exclude[array.length-1]);
    }

    public static void main(String[] args) {
	int[] array = {1,1,2,2,2,3,3,5};
	for (int n : array) {
	    System.out.print(n + " ");
	}
	System.out.println();
	int res = getMaxSum(array);
	System.out.println("Maximum sum with no adjacent number is " + res);
    }
}
