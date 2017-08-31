/*
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * 
 * 
 * OJ's undirected graph serialization:
Nodes are labeled uniquely.
 * 
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * 
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * 
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 * 
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 */

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */

// Solution 2:
// use HashMap to store nodes and their copies
// use DFS, require less memory
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return clone(node, map);
    }
    
    private UndirectedGraphNode clone(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);    // this node has been cloned, get it from the map
        
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node, clone);     // store it to the map, indicating it has been visited
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor, map));       // add copies of neighbors to the clone's neighbors list
        }
        return clone;
    }
}

// Solution 1:
// use HashMap to store nodes and their copies
// use queue to perform BFS
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return node;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        map.put(node, new UndirectedGraphNode(node.label));    // create a clone for the root
        Queue<UndirectedGraphNode> q = new LinkedList<>();  // use a queue to store original nodes that are about to be visited
        q.offer(node);
        while (!q.isEmpty()) {
            UndirectedGraphNode p = q.poll(), clone = map.get(p);   // get a node and its copy
            for (UndirectedGraphNode neighbor : p.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    q.offer(neighbor);  // if this neighbor doesn't have a clone, it has not been visited yet
                }
                clone.neighbors.add(map.get(neighbor));     // connect clone neighbors
            }
        }
        return map.get(node);
    }
}