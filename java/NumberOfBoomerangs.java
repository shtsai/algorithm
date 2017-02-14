/*
 * Given n points in the plane that are all pairwise distinct, 
 * a "boomerang" is a tuple of points (i, j, k) 
 * such that the distance between i and j equals the distance between i and k 
 * (the order of the tuple matters).
 *
 * Find the number of boomerangs. 
 * You may assume that n will be at most 500 and coordinates of points are 
 * all in the range [-10000, 10000] (inclusive).
 */

public class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int result = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // calculate the distance from i to all other points
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;       // skip the point itself
                int dist = distance(points[i], points[j]);
                if (map.containsKey(dist)) {
                    map.put(dist, map.get(dist)+1);
                } else {
                    map.put(dist, 1);
                }
            }
            
            for (int val : map.values()) {
                result += val * (val - 1);   // need to add all possible permutation
            }
            map.clear();
        }
        
        return result;
    }
    
    // a helper function used to calculate distance
    public int distance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }
}