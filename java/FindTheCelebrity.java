/*
    Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

    Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

    You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

    Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 */

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

// Solution 2: two passes
// When we call knows(a, b), there are two possible results:
//      1. true, a knows b
//         This implies that a is not a celebrity because a celebrity doesn't know anyone
//         b now becomes a potential celebrity because someone know him
//      2. false, a doesn't know b
//         This implies that a is a potential celebrity, because a celebrity doesn't know anyone
//         b is not a celebrity because a celebrity must be known by everyone
// Therefore, each call of knows(a, b) eliminates one person.
// We can use one scan through the array to nail down a candidate.
// Then we check if that candidate doesn't know anyone else and is known by everyone.
//
// Time: O(n) -two passes
// Space: O(1)
// 11/07/2017

// Version 2:
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; ++i) {
            if (knows(candidate, i)) candidate = i;      // switch candidate
        }
        for (int i = 0; i < candidate; ++i) {
            if (knows(candidate, i)) return -1;     // make sure candidate doesn't know [0, candidate-1] 
        }    
        for (int i = 0; i < n; ++i) {
            if (!knows(i, candidate)) return -1;
        }
        return candidate;
    }
}

// Version 1:
public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {  
                candidate = i;      // switch candidate
            }
        } 
        for (int i = 0; i < n; i++) {
            if (candidate != i && (!knows(i, candidate) || knows(candidate, i))) {
                return -1;
            }
        }
        return candidate;
    }
}



// Solution 1: Brute force
// Check every pair and record the number of incoming and outgoing edges
// for each node.
// The celebrity is the node with n-1 incoming edges and 0 outgoing edges.
//
// Time: O(n^2)
// Space: O(n) - two arrays
// 11/07/2017

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int[] indegree = new int[n];
        int[] outdegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (knows(i, j)) {
                    indegree[j]++;
                    outdegree[i]++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (indegree[i] == n-1 && outdegree[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}