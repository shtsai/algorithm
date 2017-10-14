/*
 * Implement the following operations of a stack using queues.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * Notes:
 * You must use only standard operations of a queue -- which means only push to back, 
 * peek/pop from front, size, and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. 
 * You may simulate a queue by using a list or deque (double-ended queue), 
 * as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 */

// reference: https://leetcode.com/articles/implement-stack-using-queues/

// Solution 2: Use two Queues
// One queue is used for storing elements, 
// the other queue is used for performing pop().
// pop() is done by moving (n-1) elements to another queue,
// and the remaining one element is what are going to pop.
// Time: O(1) push, O(n) pop
// Space: O(n) - two queues
// 10/14/2017

class MyStack {
    Queue<Integer> inqueue;
    Queue<Integer> outqueue;
    Integer peek;
    
    /** Initialize your data structure here. */
    public MyStack() {
        inqueue = new LinkedList<>();
        outqueue = new LinkedList<>();
        peek = null;
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        inqueue.offer(x);
        peek = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        peek = null;
        return getLast();
    }
    
    /** Get the top element. */
    public int top() {
        if (peek != null) {
            return peek;
        }
        peek = getLast();
        inqueue.offer(peek);
        return peek;
    }
    
    /** This function returns the last element of the stack */
    private int getLast() {
        int size = inqueue.size();
        for (int i = 1; i < size; i++) {
            outqueue.offer(inqueue.poll());
        }
        int res = inqueue.poll();
        inqueue = outqueue;     // restore inqueue
        outqueue = new LinkedList<>();       // clear outqueue
        return res;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return inqueue.isEmpty();
    }
}

// Solution 1: Use one queue 
// Rotate the queue by (n-1) after adding a new element,
// so that the last element will be shifted to the end and
// the all elements are in reversed insert order.
// Time: O(n) push, O(1) pop
// Space: O(n) - one queue
// 10/14/2017

public class MyStack {
    
    private Queue<Integer> q;
    

    public MyStack() {
        q = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        q.offer(x);
        for (int i = 0; i < q.size() - 1; i++) {
            q.offer(q.poll());
        }
    }
    
    public int pop() {
        return q.poll();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}


/*
// Solution 2: use two queues, add new element to the empty queue, then fill the queue with the rest of the elements.
public class MyStack {
    
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    
    public MyStack() {
        q1 = new LinkedList<Integer>();
        q2 = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        if (q1.isEmpty()) {  // find the empty queue,
            q1.offer(x);     // add the new element into it
            while (!q2.isEmpty()) {     // then add all elements in the other queue to this queue
                q1.offer(q2.poll());
            }
        } else {
            q2.offer(x);     // add the new element into it
            while (!q1.isEmpty()) {     // then add all elements in the other queue to this queue
                q2.offer(q1.poll());
            }
        }
    }
    
    public int pop() {
        if (q1.isEmpty()){
            return q2.poll();
        } else {
            return q1.poll();
        }
    }

    public int top() {
        if (q1.isEmpty()){
            return q2.peek();
        } else {
            return q1.peek();
        }
    }
    
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}
*/
