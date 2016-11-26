/*
 * Given an Iterator class interface with methods: next() and hasNext(),
 * design and implement a PeekingIterator that support the peek() operation
 * -- it essentially peek() at the element that will be returned by the next call to next().
 *
 * Here is an example. 
 * Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element. 
 * Calling hasNext() after that should return false.
 */

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

// main idea is to use a variable to cache the next value in this class
// therefore, when user calls peek(), it already has the next value
class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> i;
    private Integer next = null;

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    i = iterator;
	    if (i.hasNext()) {
	        next = i.next();
	    }
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    Integer result = next;
	    if (i.hasNext()) {
	        next = i.next();
	    } else {
	        next = null;
	    }
	    return result;
	}

	@Override
	public boolean hasNext() {
	    return next != null;
	}
}