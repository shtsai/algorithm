/*
    Implement an iterator to flatten a 2d vector.

    For example,
    Given 2d vector =

    [
      [1,2],
      [3],
      [4,5,6]
    ]
    By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

    Follow up:
    As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

// Solution 1:
// Use two iterators, one for rows, one for columns.
// 10/08/2017

public class Vector2D implements Iterator<Integer> {
    Iterator<List<Integer>> rowIt;
    Iterator<Integer> colIt;
    
    public Vector2D(List<List<Integer>> vec2d) {
        rowIt = vec2d.iterator();
        if (rowIt.hasNext()) {
            colIt = rowIt.next().iterator();
        }
    }

    @Override
    public Integer next() {
        return colIt.next();
    }

    @Override
    public boolean hasNext() {
        if (colIt != null && colIt.hasNext()) return true;        
        while (rowIt.hasNext()) {
            colIt = rowIt.next().iterator();
            if (colIt.hasNext()) {
                return true;
            }
        }
        return false;
    }
}
