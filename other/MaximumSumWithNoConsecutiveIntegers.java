/*
    Given a multiset of integers, find the subset of integers that produces the maximum sum, such that no two numerically consecutive integers (i.e., n and n+1) are in the subset. Return the maximum sum.

    INPUT:  {1, 1, 1, 2, 2, 3, 6}
    DP:   0 [1, 2, 3, 2, 4, 6, 12]
    OUTPUT: 12
    EXPLANATION: Take the 1's, 3's, and 6's.

    INPUT:  {1, 1, 1, 2, 3, 4, 6}
    DP:   0 [1, 2, 3, 3, 6, 7, 13]  

    Rubrik second interview
 */

import java.io.*;
import java.util.*;

// Solution 2: Dynamic Programming with Treemap
// Store the integers as (number, count) pairs in a treemap.
// Treemap will keep pairs in the sorted order of their keys (number)
// so that we can quickly (logn) find the largest valid element.
// In addition to the treemap, we also have a DP array.
// In this case, each cell i contains the maximum sum in range [0: i].
// Note that the maximum sum at cell i does not have to contains number i.
// However, using this approach, we need to take extra care of the keys
// and the indices of the dp array. We need an extra mapping.
//
// Time: O(nlog)
// Space: O(n)
// 10/27/2017
//
// Note: the code below has not been tested in an OJ.

class Solution {
    public static void main(String[] args) {
        int[] array = new int[] {1, 1, 1, 2, 2, 3, 6};
        System.out.println(maximumSum(array));
    }
  
    public static int maximumSum (int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n : nums) {    // build frequency mapping
            if (!map.containsKey(n)) {
                map.put(n, 1); 
            } else {
                map.put(n, map.get(n) + 1);
            }
        }  
  
        int[] dp = new int[map.size()];
        HashMap<Integer, Integer> mapping = new HashMap<>();
        int index = 0;
        for (int i : map.keySet()) {
            mapping.put(i, index);
            index++;
        }
  
        dp[0] = map.get(map.firstKey());

      
        for (Integer i : map.keySet()) {
      
            Integer key = map.lowerKey(map.get(i)-1);  // find the largest key that is smaller than nums[i]-1
          int previous = mapping.get(i) == 0 ? 0 : dp[mapping.get(i)-1];  
          if (key == null) {
                
                dp[mapping.get(i)] = Math.max(map.get(i), previous); 
            } else {    // found a valid choice
            
                dp[mapping.get(i)] = Math.max(map.get(i) + dp[mapping.get(i)], previous);
            }
        }
        
        return dp[mapping.get(map.lastKey())];
  
    }
}

// Solution 1: Dynamic programming
// Building a DP array
// Each cell contains the maximum non-consecutive sum 
// containing the current number.
// At each cell, we need to look back and find the large sum
// available ending with a valid number.
// Time: O(n^2)
// Space: O(n)
// 10/27/2017

