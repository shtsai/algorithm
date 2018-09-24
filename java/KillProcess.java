/*
	Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

	Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

	We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

	Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

	Example 1:
	Input: 
	pid =  [1, 3, 10, 5]
	ppid = [3, 0, 5, 3]
	kill = 5
	Output: [5,10]
	Explanation: 
	           3
	         /   \
	        1     5
	             /
	            10
	
	Kill 5 will also kill 10.
	
	Note:
	The given kill id is guaranteed to be one of the given PIDs.
	n >= 1.
 */

// Solution 2: BFS
// Build graph, then do BFS
// Time: O(n)
// Space: O(n)
// 09/24/2018
class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            edges.computeIfAbsent(ppid.get(i), x -> new ArrayList<>()).add(pid.get(i));
        }
        List<Integer> res = new ArrayList<>();
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(kill);
        while (!q.isEmpty()) {
            int k = q.poll();
            res.add(k);
            if (edges.containsKey(k)) {
                for (int child : edges.get(k)) {
                    q.offer(child);
                }
            }
        }
        return res;
    }
}

// Solution 1: DFS
// Build graph, then do DFS
// Time: O(n)
// Space: O(n)
// 09/24/2018
class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        HashMap<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            edges.computeIfAbsent(ppid.get(i), x -> new ArrayList<>()).add(pid.get(i));
        }
        
        List<Integer> res = new ArrayList<>();
        killer(edges, res, kill);
        return res;
    }
    
    private void killer(HashMap<Integer, List<Integer>> edges, List<Integer> res, int target) {
        res.add(target);
        if (edges.containsKey(target)) {
            for (Integer child : edges.get(target)) {
                killer(edges, res, child);
            }
        }
    }
}