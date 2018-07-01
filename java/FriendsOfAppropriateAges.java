/*
    Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 

    Person A will NOT friend request person B (B != A) if any of the following conditions are true:

    age[B] <= 0.5 * age[A] + 7
    age[B] > age[A]
    age[B] > 100 && age[A] < 100
    Otherwise, A will friend request B.

    Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

    How many total friend requests are made?

    Example 1:

    Input: [16,16]
    Output: 2
    Explanation: 2 people friend request each other.
    Example 2:

    Input: [16,17,18]
    Output: 2
    Explanation: Friend requests are made 17 -> 16, 18 -> 17.
    Example 3:

    Input: [20,30,100,110,120]
    Output: 
    Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
     

    Notes:

    1 <= ages.length <= 20000.
    1 <= ages[i] <= 120.
 */

// Solution 2: Counting 
// Count the frequency of each age [1, 120]
// Then compare every pair of ages and add valid pairs.
// Note that we need to be careful not to double count.
// Time: O(n + A^2) - n:length of ages, A: number of valid ages
// Space: O(A)
// 07/01/2018
    
class Solution {
    public int numFriendRequests(int[] ages) {
        int[] freq = new int[121];
        for (int age : ages) {
            freq[age]++;
        }
        int res = 0; 
        for (int A = 1; A <= 120; A++) {
            for (int B = 1; B <= 120; B++) {
                if (B <= A * 0.5 + 7) {
                    continue;
                } else if (A < B) {
                    continue;
                } else {
                    res += freq[A] * freq[B];
                }
                
                if (A == B) {
                    res -= freq[A];
                }
            }
        }
        return res;
    }
}

// Solution 1: Brute force
// Check every pair
// Time: O(n^2)
// Space: O(1)
// 06/26/2018

class Solution {
    public int numFriendRequests(int[] ages) {
        int res = 0;
        for (int A = 0; A < ages.length; A++) {
            for (int B = 0; B < ages.length; B++) {
                if (A == B) {
                    continue;
                } else if ((ages[A] * 0.5 + 7 >= ages[B]) ||
                           (ages[A] < ages[B])) {
                    continue;
                } else {
                    res++;
                }
            }
        }
        return res;
    }
}