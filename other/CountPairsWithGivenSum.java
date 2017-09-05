/*
 * Give an array, count the number of pairs whose sum equals to the give target
 *
 * Use HashMap to store the count of each integer,
 * traverse the array and find if other element (target-currentNum) exist.
 * Note that result needs to be divided by two, b/c we are double counting
 * 
 * time: O(n), space: O(n)
 *
 * reference: www.geeksforgeeks.org/count-pairs-with-given-sum/
 */

import java.util.*;

class CountPairsWithGivenSum {
    public static int countPairsWithGivenSum(int[] array, int sum) {
	HashMap<Integer, Integer> map = new HashMap<>();
	for (int i : array) {   // add the frequency of each int
	    if (!map.containsKey(i)) {
		map.put(i, 1);
	    } else {
		map.put(i, map.get(i)+1);
	    }
	}
	int count = 0;
	for (int i : array) {
	    if (map.containsKey(sum-i)) {
		count += map.get(sum-i);
		if (i == sum - i) count--; // cannot use one int twice, remove self
	    }
	}
	return count / 2;   // b/c we double count everything
    }

    public static void main(String[] args) {
	int[] array = {1, 1, 1, 1};
	int sum = 2;
	int res = countPairsWithGivenSum(array, sum);
	System.out.println(res);
    }
}
