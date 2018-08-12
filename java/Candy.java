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

// Solution 2: Two scans
// Scan from left to right, then right to left.
// Find the required number of candies for each position.
// Time: O(n) - three passes
// Space: O(n)
// 08/11/2018

// version 2: One array
class Solution {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int res = candies[ratings.length - 1];
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            res += candies[i];
        }
        return res;
    }
}

// version 1: Two arrays
class Solution {
    public int candy(int[] ratings) {
        int[] leftToRight = new int[ratings.length];
        int[] rightToLeft = new int[ratings.length];
        Arrays.fill(leftToRight, 1);
        Arrays.fill(rightToLeft, 1);
        
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i - 1] < ratings[i]) {
                leftToRight[i] = leftToRight[i - 1] + 1;
            }
        }
        
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                rightToLeft[i] = rightToLeft[i + 1] + 1;
            }
        }
        
        int res = 0;
        for (int i = 0; i < ratings.length; i++) {
            res += Math.max(leftToRight[i], rightToLeft[i]);
        }
        return res;
    }
}

// Solution 1: Naive solution
// Repeatedly scan through array and check for requirement,
// until all elements satisfy and no more changes.
// Time: O(n^2) - Time limit exceeded
// Space: O(1)
// 08/11/2018

class Solution {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        boolean updated = false;
        while (true) {
            for (int i = 0; i < candies.length; i++) {
                if (i > 0 && ratings[i - 1] < ratings[i] && candies[i - 1] + 1 > candies[i]) {
                    candies[i] = candies[i - 1] + 1;
                    updated = true;
                }
                if (i < candies.length - 1 && ratings[i + 1] < ratings[i] && candies[i + 1] + 1 > candies[i]) {
                    candies[i] = candies[i + 1] + 1;
                    updated = true;
                }
            }
            if (!updated) {
                break;
            } else {
                updated = false;
            }
        }
        
        int res = 0;
        for (int candy : candies) {
            res += candy;
        }
        return res;
    }
}
