/*
	We are given an array asteroids of integers representing asteroids in a row.

	For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).

	Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode.

	Example 1:
	Input: 
	asteroids = [5, 10, -5]
	Output: [5, 10]
	Explanation: 
	The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
	Example 2:
	Input: 
	asteroids = [8, -8]
	Output: []
	Explanation: 
	The 8 and -8 collide exploding each other.
	Example 3:
	Input: 
	asteroids = [10, 2, -5]
	Output: [10]
	Explanation: 
	The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
	Note:

	The length of asteroids will be at most 10000.
	Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 */

// Solution 1: Stack
// Use stack to store positive integers.
// When encounter negative integers, pop from the stack if the top element is smaller.
// Then build the result from the stack.
//
// Time: O(n)
// Space: O(n)
// 11/25/2017

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        
        for (int a : asteroids) {
            if (a < 0) {
                while (!stack.isEmpty() && stack.peek() < a * -1) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == a * -1) {
                    stack.pop();
                } else if (stack.isEmpty()) {
                    res.add(a);
                }
            } else {
                stack.push(a);
            }
        }
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            res.add(temp.pop());
        }
        int[] array = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            array[i] = res.get(i);
        }
        return array;
    }
}