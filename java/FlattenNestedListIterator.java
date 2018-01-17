/*
	Given a nested list of integers, implement an iterator to flatten it.

	Each element is either an integer, or a list -- whose elements may also be integers or other lists.

	Example 1:
	Given the list [[1,1],2,[1,1]],

	By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

	Example 2:
	Given the list [1,[4,[6]]],

	By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

// Solution 2: Stack and iterator
// Store the current integer in a varible.
// next() returns the current integer.
// hasNext() checks if the current integer is null.
// If it is, call getNext() to get next integer.
// Use stack to store the iterators of lists, from lower level to higher level.
// Push iterator of sublists to the stack, and pop from stack when it reaches the end.
// 01/17/2018

public class NestedIterator implements Iterator<Integer> {

    Integer current;
    Stack<Iterator<NestedInteger>> stack;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        current = null;
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Integer res = current;
            current = null;
            return res;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        if (current != null) {
            return true;
        } else {
            return getNext();
        }
    }
    
    private boolean getNext() {
        while (!stack.isEmpty()) {
            Iterator<NestedInteger> it = stack.peek();
            if (it.hasNext()) {
                NestedInteger next = it.next();
                if (next.isInteger()) {
                    current = next.getInteger();
                    return true;
                } else {
                    stack.push(next.getList().iterator());
                }
            } else {
                stack.pop();
            }
        }
        return false;
    }
}

// Solution 1: Flatten list, then iterate
// Recursively flatten smaller lists.
// Time: O(n) - initialization
//		 O(1) - next()
//		 O(1) - hasNext()
// Space: O(n)
// 01/15/2018

public class NestedIterator implements Iterator<Integer> {
    List<Integer> list;
    int index;
    
    public NestedIterator(List<NestedInteger> nestedList) {
        this.list = flatten(nestedList);
        index = 0;
    }
    
    private List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> res = new ArrayList<>();
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                res.add(ni.getInteger());
            } else {
                res.addAll(flatten(ni.getList()));
            }
        }
        return res;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Integer res = this.list.get(this.index);
            this.index++;
            return res;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        return this.index < this.list.size();
    }
}
