/*
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
 * 
 * Example 1:
 * Input: [5, 4, 3, 2, 1]
 * Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
 * Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
 * For the left two athletes, you just need to output their relative ranks according to their scores.
 * Note:
 * N is a positive integer and won't exceed 10,000.
 * All the scores of athletes are guaranteed to be unique.
 */

// Solution 2:
// sort the (score, index) pairs
// O(nlgn) time, O(n) space
public class Solution {
    public String[] findRelativeRanks(int[] nums) {
        
        // initialize a ranking array
        int[][] ranking = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            ranking[i][0] = nums[i];    // [0] is the score, which will be sorted in decending order
            ranking[i][1] = i;          // [1] is the corresponding index in the original array
        }
        
        Arrays.sort(ranking, (a, b) -> b[0]-a[0]);  // use lambda expression to sort in decending order
        
        String[] result = new String[nums.length];
        
        for (int i = 0; i < result.length; i++) {
            if (i == 0) result[ranking[i][1]] = "Gold Medal";
            else if (i == 1) result[ranking[i][1]] = "Silver Medal";
            else if (i == 2) result[ranking[i][1]] = "Bronze Medal";
            else result[ranking[i][1]] = Integer.toString(i + 1);
        }
        
        return result;
    }
}

// Solution 1:
// Brute force, compare every pair
// O(n) time
public class Solution {
    public String[] findRelativeRanks(int[] nums) {
        
        // initialize a ranking array
        int[] ranking = new int[nums.length];
        for (int i = 0; i < nums.length; i++) ranking[i] = nums.length;
        
        // update rankings
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) ranking[i]--; 
                else ranking[j]--;
            }
        }
        
        String[] result = new String[nums.length];
        
        for (int i = 0; i < ranking.length; i++) {
            if (ranking[i] == 1) result[i] = "Gold Medal";
            else if (ranking[i] == 2) result[i] = "Silver Medal";
            else if (ranking[i] == 3) result[i] = "Bronze Medal";
            else result[i] = Integer.toString(ranking[i]);
        }
        
        return result;
    }
}