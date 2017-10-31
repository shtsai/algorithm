/*
	There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

	Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

	Example 1:
	Input: 
	[[1,1,0],
	 [1,1,0],
	 [0,0,1]]
	Output: 2
	Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
	The 2nd student himself is in a friend circle. So return 2.
	Example 2:
	Input: 
	[[1,1,0],
	 [1,1,1],
	 [0,1,1]]
	Output: 1
	Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
	so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
	Note:
	N is in range [1,200].
	M[i][i] = 1 for all students.
	If M[i][j] = 1, then M[j][i] = 1.
 */

// Solution 2: Union Find
// Use a parent array to keep track of the parent of each node.
// Each connected component will have a same root.
// Parents of each node is initialized to -1. 
// When find a path between two nodes, use union() to join
// two nodes into a component.
// Finally, count the number of root nodes.
//
// Time: O(n^3) - scan through n^2 matrix, each union is O(n)
// Space: O(n) - parent array
// 10/31/2017

class Solution {
    private int find (int[] parent, int i) {
        while (parent[i] != -1) {
            i = parent[i];
        }
        return i;
    }
    
    private void union (int[] parent, int i, int j) {
        int iParent = find(parent, i);
        int jParent = find(parent, j);
        if (iParent != jParent) {
            parent[iParent] = jParent;
        }
    }
    
    public int findCircleNum(int[][] M) {
        int[] parent = new int[M.length];
        Arrays.fill(parent, -1);
        
        for (int i = 0; i < M.length; i++) {
            for (int j = i+1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        
        int res = 0;
        for (int i = 0; i < M.length; i++) {
            if (parent[i] == -1) {     // root == parent is itself
                res++;
            }
        }
        
        return res;
    }
}

// Solution 1: DFS
// Convert the problem into a graph problem, looking for number of 
// connected components.
// Use a boolean array to keep track of whether we have visited a node.
// Use recursive function to search through the graph.
//
// Time O(n^2) - completely scan n^2 matrix
// Space: O(n)
// 10/31/2017

class Solution {
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        
        boolean[] visited = new boolean[M.length];
        int counter = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                visit(M, visited, i);
                counter++;
            }
        }
        
        return counter;
    }
    
    private void visit(int[][] M, boolean[] visited, int current) {
        visited[current] = true;
        for (int i = 0; i < M.length; i++) {
            if (M[current][i] == 1 && i != current && !visited[i]) {
                visit(M, visited, i);
            }
        }
    }
}