/*
 * There are N children standing in a line. Each child is assigned a rating value.
 * 
 * You are giving candies to these children subjected to the following requirements:
 * 
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * 
 */

// Solution 1:
// two pass, left to right, then right to left
// make sure the number of candy satisfies the requirement from both sides
// reference: https://discuss.leetcode.com/topic/25985/simple-o-n-java-solution-with-comments
class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int[] res = new int[ratings.length];
        res[0] = 1;
        for (int i = 1; i < ratings.length; i++) {      // left to right
            if (ratings[i] > ratings[i-1]) {
                res[i] = res[i-1]+1;
            } else {
                res[i] = 1;
            }
        }
        
        for (int i = ratings.length-2; i >= 0; i--) {   // right to left
            if (ratings[i] > ratings[i+1]) {
                res[i] = Math.max(res[i], res[i+1] + 1);
            }
        }
        
        int sum = 0;
        for (int i : res) sum += i;
        return sum;
    }
}