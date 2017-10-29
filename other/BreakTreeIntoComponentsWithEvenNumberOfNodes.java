/*
    Rubrik third interview

    Given  a tree where each node can have any number of children.
    Whenever you remove an edge from the tree, it breaks into two
    components.
    You have to remove as many edges as possible such that the total number
    of nodes in each component (obtained after breaking the edges) is an even number

    struct Node {
     std::vector<Node *> children
    }

    Input: Node *root
    Output: std:vector<Node *> components // list containing pointers to root of each of the component

                a
             b     c
          d
          
          
           7
           a 
       4       1  1
       b       c  g 
    1    2
    d    e       
           1
           f
           
             4
             a
          1  1  1
          b  c  d  
           
        
        
       4   
       b       
    1    2
    d    e       
           1
           f
           
         
           7
           a 
       4       1  1
       b       c  g 
    1    2
    d    e       
           1
           f


          4
          a
        3
        b
      2  
      c
    1
    d  

 */
 
// Solution 1: (DOES NOT ALWAYS RETURN OPTIMAL RESULT)
// Greedy Approach
// First we compute the number of children for each node.
// If the root has odd number of children, this tree is invalid,
// throw any exception.
// Then we recursively call removeHelper() on child nodes with even
// number of children.
// Time: O(n) - total number of nodes
// Space: O(n) - count map for each node + logn call stack
// 10/27/2017


// Main function
public List<Node> removeEdges(Node root) {
    if (root == null) return null;
    List<Node> res = new ArrayList<>();
    HashMap<Node, Integer> map = HashMap<>();
    countChildren(root, map);
    if (map.get(root) % 2 == 1) throw illegalArgumentException();
    
    removeHelper(res, root, map);
    return res;
}

// recursive function that removes edges to children with even number of children
public void removeHelper(List<Node res, Node root, HashMap<Node, Integer> map) {
    if (root == null || root.children.size() == 0) return;

    int[] count = new int[root.children.size()];
    int sum = map.get(root);  
    
    for (int i = 0; i < root.children.size(); i++) {
        Node child = root.children.get(i);
        if (count[i] % 2 == 0) {
            root.children.remove(child);
        }
        removeHelper(res, child, map);
    }
    
    if (sum % 2 == 0) {
      res.add(root);
    }
}

// helper function that return the number of children
public int countChildren(Node node, HashMap<Node, Integer> map) {
    if (node == null) return 0;
    if (map.containsKey(node)) {
        return map.get(node);
    }
    if (node.children.size() == 0) {
       map.put(node, 1);
       return 1;
    }
    int count = 1;
    for (int i = 0; i < node.children.size(); i++) {
        count += countChilren(node.children.get(i));
    }
    
    map.put(node, count);
    return count;
}