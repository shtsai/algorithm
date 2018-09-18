/*
    Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.
  
    Example:
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();   --> Returns -3.
    minStack.pop();
    minStack.top();      --> Returns 0.
    minStack.getMin();   --> Returns -2.
 */

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

// Solution 2: 
// Only update mins when necessary
// More space efficient
// Time: push - O(1)
//       pop - O(1)
//       top - O(1)
//       getMin - O(1)
// Space: O(n) 
// 09/18/2018
class MinStack {
    Stack<Integer> values;
    Stack<Integer> mins;
    
    public MinStack() {
        values = new Stack<>();
        mins = new Stack<>();
    }
    
    public void push(int x) {
        values.push(x);
        if (mins.isEmpty() || x <= mins.peek()) {
            mins.push(x);
        } 
    }
    
    public void pop() {
        if (values.pop().equals(mins.peek())) {
            mins.pop();
        }
    }
    
    public int top() {
        return values.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }
}

// Solution 1: Two stacks
// Time: push - O(1)
//       pop - O(1)
//       top - O(1)
//       getMin - O(1)
// Space: O(2n) = O(n) - two stacks of size n
// 09/18/2018
class MinStack {
    Stack<Integer> values;
    Stack<Integer> mins;
    
    public MinStack() {
        values = new Stack<>();
        mins = new Stack<>();
    }
    
    public void push(int x) {
        values.push(x);
        if (mins.isEmpty() || x < mins.peek()) {
            mins.push(x);
        } else {
            mins.push(mins.peek());
        }
    }
    
    public void pop() {
        values.pop();
        mins.pop();
    }
    
    public int top() {
        return values.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }
}

