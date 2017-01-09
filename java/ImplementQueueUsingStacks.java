/*
 * Implement the following operations of a queue using stacks.
 *
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 *
 */

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