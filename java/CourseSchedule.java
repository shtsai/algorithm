/*
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * For example:
 *
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 *
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * 
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */

// Improved version of solution 1:
// replace adjacency matrix with adjacency list, save space and increase spped
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] list = new List[numCourses];   // adjacency list
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            list[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            list[prerequisites[i][1]].add(prerequisites[i][0]);   // add this edge to the list
            inDegree[prerequisites[i][0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        int count = 0;      // counter for deleted nodes
        
        for (int i = 0; i < inDegree.length; i++) {     // insert all nodes with no incoming edge
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int outNode : list[node]) {
                inDegree[outNode]--;
                if (inDegree[outNode] == 0) q.offer(outNode);
            }
            count++;
        }
        
        return count == numCourses;
    }
}


// Solution 1:
// convert edge list into an adjacency matrix, then do topological sort
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];   // adjacency matrix
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < prerequisites.length; i++) {
            matrix[prerequisites[i][1]][prerequisites[i][0]] = 1;   // convert lists of edges into a matrix
            inDegree[prerequisites[i][0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        int count = 0;      // counter for deleted nodes
        
        for (int i = 0; i < inDegree.length; i++) {     // insert all nodes with no incoming edge
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int i = 0; i < numCourses; i++) {
                if (matrix[node][i] == 1) {     // find an outgoing edge
                    inDegree[i]--;
                    if (inDegree[i] == 0) q.offer(i);
                }
            }
            count++;
        }
        
        return count == numCourses;
    }
}

// Solution 2:
// DFS search, mark nodes as visited, unvisited, and currently visiting
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];   // adjacency matrix
        int[] status = new int[numCourses];     // -1: visited, 0: unvisited, 1:currently visiting
        
        for (int i = 0; i < prerequisites.length; i++) {
            matrix[prerequisites[i][1]][prerequisites[i][0]] = 1;   // convert lists of edges into a matrix
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {     // insert all nodes to the set
            set.add(i);
        }
        
        // DFS search
        while (!set.isEmpty()) {
            int node = set.iterator().next();
            if (visit(node, status, matrix, set) < 0) return false;
        }
        
        return true;
    }
    
    private int visit(int node, int[] status, int[][] matrix, Set<Integer> set) {
        if (status[node] > 0) return -1;    // find a loop
        status[node] = 1;  // mark the node as currently visiting
        
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[node][i] == 1 && status[i] != -1) {     // outgoing edge to an unvisited node
                if (visit(i, status, matrix, set) < 0) return -1;
            }
        }
        
        status[node] = -1;  // finish visiting this node
        set.remove(node);
        return 1;
    }
}