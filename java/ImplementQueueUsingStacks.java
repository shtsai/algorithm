/*
    Implement the following operations of a queue using stacks.

    push(x) -- Push element x to the back of queue.
    pop() -- Removes the element from in front of queue.
    peek() -- Get the front element.
    empty() -- Return whether the queue is empty.
    Notes:
    You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
    Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
    You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue). 
 */

// Solution 1 version 2:
// Add function for moving elements from instack to outstack.
// Avoid code repetition. 
// 10/14/2017

class MyQueue {
    Stack<Integer> instack;
    Stack<Integer> outstack;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        instack = new Stack<>();
        outstack = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        instack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outstack.isEmpty()) {
            moveToOut(instack, outstack);
        }
        return outstack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (outstack.isEmpty()) {
            moveToOut(instack, outstack);
        }
        return outstack.peek();
    }
    
    /** Transfer all elements in instack to outstack */
    private void moveToOut(Stack<Integer> instack, Stack<Integer> outstack) {
        while (!instack.isEmpty()) {
            outstack.push(instack.pop());
        }
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return instack.isEmpty() && outstack.isEmpty();
    }
}

// Solution 1:
class MyQueue {
    private Stack<Integer> instack = new Stack<Integer>(); // back end of the queue
    private Stack<Integer> outstack = new Stack<Integer>(); // front end of the queue
    
    // Push element x to the back of queue.
    public void push(int x) {
        instack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (!outstack.isEmpty()) {
            outstack.pop();
        } else { // if the back end is empty, tranfer front end to the back end
            while (!instack.isEmpty()) { 
                outstack.push(instack.pop());
            }
            outstack.pop();
        }
    }

    // Get the front element.
    public int peek() {
        if (!outstack.isEmpty()) {
            return outstack.peek();
        } else { // if the back end is empty, tranfer front end to the back end
            while (!instack.isEmpty()) {
                outstack.push(instack.pop());
            }
            return outstack.peek();
        }
    }

    // Return whether the queue is empty.
    public boolean empty() { // check if both stacks are empty
        return instack.isEmpty() && outstack.isEmpty();
    }
}