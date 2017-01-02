/*
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 */

// optimized version (using two stacks)
public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minstack;


    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minstack = new Stack<Integer>();
    }
    
    public void push(int x) {
        // if the current element is smaller than or equal to the min
        // push it the the min stack
        if (minstack.empty() || minstack.peek() >= x) {
            minstack.push(x);
        }
    
        stack.push(x);
    }
    
    public void pop() {
        if (!stack.empty()) {
            int pop = stack.pop();
            if (pop == minstack.peek()) {  // pop an element from min stack if it is the current min
                minstack.pop();
            }
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minstack.peek();
    }
}

/*  use O(2n) space
public class MinStack {

    // min stack maintains the min at each level of the stack
    private Stack<Integer> stack;
    private Stack<Integer> minstack;

    public MinStack() {
        stack = new Stack<Integer>();
        minstack = new Stack<Integer>();
    }
    
    public void push(int x) {
        if (minstack.empty() || minstack.peek() > x) {
            minstack.push(x);
        } else {
            minstack.push(minstack.peek());
        }
        
        stack.push(x);
        
    }
    
    public void pop() {
        stack.pop();
        minstack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minstack.peek();
    }
}
*/

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */