/*
    There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

    Example 1:
    Given the following words in dictionary,

    [
      "wrt",
      "wrf",
      "er",
      "ett",
      "rftt"
    ]
    The correct order is: "wertf".

    Example 2:
    Given the following words in dictionary,

    [
      "z",
      "x"
    ]
    The correct order is: "zx".

    Example 3:
    Given the following words in dictionary,

    [
      "z",
      "x",
      "z"
    ]
    The order is invalid, so return "".

    Note:
    You may assume all letters are in lowercase.
    You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.
 */

// Solution 1: Topological sort
// We can convert this problem into a graph problem, and
// we need return the topological sorting of the graph.
// If there is a cycle in the graph, we return "".
//
// First, we can scan through the dictionary to initialize
// the node and indegree counter for each character.
// Since the words are already sorted in lexicographic order,
// we can just compare each adjacent pair of words.
// So we start comparing from the first index 0:
//      1. if the two characters are the same, we move to next index
//      2. if the two characters are different, since all 
//         previous characters are all the same and these 
//         characters are at the same index, we know the first
//         character needs to be placed before the second.
//         This is equivalent to having an edge from first
//         character's node to the second character's node.
//         We also need to increment indegree count for the second.
//
// After building this graph, we can perform topological sort.
// We first scan through indegree map, add all nodes with
// indegree of 0 to a queue. Then we start removing outgoing edges
// for the nodes in the queue, and add nodes to the queue when they
// have no incoming edge.
// Finally, we check if the length of stringbuilder is the same as
// the number of nodes we have. If they are the same, we have completed
// the topological sorting. If not, there must exists a cycle so that
// some nodes do not have 0 indegrees.
//
// Time: O(mn) - # of words = m, length of words = n
// Space: O(mn)
// 10/29/2017

class Solution {
    private class Node {
        char val;
        List<Node> next;
        public Node (char val) {
            this.val = val;
            next = new ArrayList<>();
        }
    }
    
    public String alienOrder(String[] words) {
        HashMap<Character, Node> map = new HashMap<>();
        HashMap<Character, Integer> indegree = new HashMap<>();
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if (!map.containsKey(c)) {
                    map.put(c, new Node(c));
                    indegree.put(c, 0);
                }
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i+1];
            for (int j = 0; j < Math.min(first.length(), second.length()); j++) {
                char firstc = first.charAt(j);
                char secondc = second.charAt(j);
                if (firstc != secondc) {
                    map.get(firstc).next.add(map.get(secondc));
                    indegree.put(secondc, indegree.get(secondc) + 1);
                    break;
                }
            }
        }    
        
        Queue<Character> q = new LinkedList<>();
        for (Character c : indegree.keySet()) {
            if (indegree.get(c) == 0) {      // zero indegree
                q.offer(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            Node cur = map.get(c);
            for (Node n : cur.next) {
                indegree.put(n.val, indegree.get(n.val)-1);
                if (indegree.get(n.val) == 0) {
                    q.offer(n.val);
                }
            }
            cur.next.clear();     
        }
        
        if (sb.length() != map.size()) {
            return "";
        }
        
        return sb.toString();
    }
}