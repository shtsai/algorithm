/*
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * 
 *  Buildings  Skyline Contour
 * 
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * 
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * 
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * 
 * Notes:
 * 
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */

// Solution 1:
// First get all critical points and sort them in accending order
// then iterate through these critical points and use a priority queue
// to quickly retrieve the current max height. 
// When max height changes, it indicates a key point
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();     // stores the height of building at each critical point
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) return res;
        
        for (int[] building : buildings) {
            height.add(new int[]{building[0], building[2]});   // add left critical point with positive height
            height.add(new int[]{building[1], -building[2]});  // add right critical point with negative height, indicating end
        }
        // sort critical points in accending order
        height.sort((int[] a, int[] b)-> {
            if (a[0] != b[0]) return a[0]-b[0];   // sort by critical point
            else return b[1]-a[1];  // if two heights at the same point, add larger ones first
        });
        
        PriorityQueue<Integer> q = new PriorityQueue<>(buildings.length, Collections.reverseOrder());     // max heap
        q.offer(0);     // starting point
        int prev = 0;   // the height of previous critical point
        for (int[] criticalPoint : height) {
            if (criticalPoint[1] > 0) {  // positive height means start point of a building
                q.offer(criticalPoint[1]);
            } else {                // negative means the end of a building
                q.remove(-criticalPoint[1]); // remove it from the queue
            }
            int newHeight = q.peek(); // when max height changes,
            if (newHeight != prev) {  // need to change at this critical point
                res.add(new int[]{criticalPoint[0], newHeight}); // this is a "key point"
            }
            prev = newHeight;   // store prev height
        }
        return res;
    }
}