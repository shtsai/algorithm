/*
    Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

    Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

    So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

    Note:
    Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
    Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
    As long as a house is in the heaters' warm radius range, it can be warmed.
    All the heaters follow your radius standard and the warm radius will the same.
    Example 1:
    Input: [1,2,3],[2]
    Output: 1
    Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
    Example 2:
    Input: [1,2,3,4],[1,4]
    Output: 1
    Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 */

// Solution 1: Binary search
// Iterate through houses, use binary search to find the two closest heaters 
// for each houses. Pick the closer heaters, and update minimum radius.
// 
// Time: O(nlogn + mlogn) - m: len(houses), n: len(heaters)
// Space: O(1)
// 02/21/2018

public class Solution {
    public int FindRadius(int[] houses, int[] heaters)
    {
        Array.Sort(heaters);
        int radius = Int32.MinValue;
        for (int i = 0; i < houses.Length; i++) {
            int index = binarySearch(heaters, houses[i]);
            if (index < 0) {
                index = -(index + 1);
            }
            int heater1 = index - 1 >= 0 ? houses[i] - heaters[index - 1] : Int32.MaxValue;
            int heater2 = index < heaters.Length ? heaters[index] - houses[i] : Int32.MaxValue;

            radius = Math.Max(radius, Math.Min(heater1, heater2));
        }
        return radius;
    }

    // Java implementation of binary search.
    // Returns index of the target if it exists.
    // Otherwise, return -(insertPosition + 1)
    public int binarySearch(int[] array, int target) {
        int low = 0, high = array.Length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -(low + 1);
    }

}