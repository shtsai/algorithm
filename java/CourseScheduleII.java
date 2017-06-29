/*
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 * 
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */

// topological sort
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] list = new List[numCourses];
        int[] inDegree = new int[numCourses];
        int[] result = new int[numCourses];
        int pointer = 0;
        
        // convert edge list to an adjacency list
        for (int i = 0; i < numCourses; i++) {
            list[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            list[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }
        
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int outNode : list[node]) {
                if (--inDegree[outNode] == 0) q.offer(outNode);
            }
            result[pointer++] = node;
        }
        
        return pointer == numCourses ? result : new int[0]; // if pointer != numCourses, there exists a loop
    }
}