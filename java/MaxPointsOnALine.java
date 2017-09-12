/*
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

// Solution 1:
// we don't calculate slope of the points and store them in the map
// b/c float numbers can have precision problem.
// Instead, we use GCD to reduce x' and y' to their simpliest form,
// and use x' and y' as the key to the hashmap, where the couter is stored.
// Note that we also need to count the number of overlapping points,
// because they can be added to any line. So we save them until the end,
// and add them to the line that contains most points.
//
// reference: https://discuss.leetcode.com/topic/2979/a-java-solution-with-notes
// time: O(n^2) - iterate through every point pairs
// space: O(n^2) - worst case, all point pairs have different slope, store them all
class Solution {
    public int maxPoints(Point[] points) {
        if (points.length <= 2) return points.length;
        // first layer is x, second layer is y, last layer is count
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        int res = Integer.MIN_VALUE;
        
        for (int i = 0; i < points.length; i++) { // start search from a node
            map.clear();    // clear map for each node
            int same = 0;   // counter for overlapping points
            int maxSoFar = 0;
            for (int j = i+1; j < points.length; j++) { // to all other nodes
                int x = points[i].x-points[j].x;
                int y = points[i].y-points[j].y;
                if (x == 0 && y == 0) { // two points are overlapped
                    same++;
                    continue;
                }
                int gcd = GCD(x, y);    // find gcd to reduce two nums to simplest form
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }
                
                if (!map.containsKey(x)) {
                    HashMap<Integer, Integer> newMap = new HashMap<>();
                    newMap.put(y, 1);
                    map.put(x, newMap);
                } else {    // contains x
                    if (!map.get(x).containsKey(y)) {   // doesn't contain y
                        map.get(x).put(y, 1);
                    } else { // contains x and y, increase counter
                        map.get(x).put(y, map.get(x).get(y)+1);
                    }
                }
                maxSoFar = Math.max(maxSoFar, map.get(x).get(y));
            }
            res = Math.max(res, maxSoFar + same + 1); // add overlapping points the line with most points so far, add 1 for the node itself
        }
        return res;
    }
    
    /* a helper function that finds GCD between two number.
       It is used to reduce the coordinates of points to their simplest form */
    private int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a%b);
    }
}