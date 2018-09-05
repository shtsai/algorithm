/*
    Given an absolute path for a file (Unix-style), simplify it.

    For example,
    path = "/home/", => "/home"
    path = "/a/./b/../../c/", => "/c"

    Corner Cases:
    - Did you consider the case where path = "/../"?
      In this case, you should return "/".
    - Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
      In this case, you should ignore redundant slashes and return "/home/foo".
 */

// Solution 2: List (Stack is not allowed)
// Same idea, but uses list.
// Time: O(n) - two passes
// Space: O(n) - list
// 11/09/2017
class Solution {
    public String simplifyPath(String path) {
        ArrayList<String> list = new ArrayList<>();
        for (String p : path.split("/")) {
            if (p.equals("") || p.equals(".")) {
                continue;
            } else if (p.equals("..")) {
                if (list.size() > 0) {
                    list.remove(list.size()-1);
                }
            } else {
                list.add(p);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (String p : list) {
            sb.append("/" + p);
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}

// Solution 1: Stack
// Split the path on "/".
// Use a stack to process the string[].
// If the a part is valid, push it to the stack.
// If a part is ".", we do nothing.
// If a part is "..", we pop a part off the stack to go back one level.
// Finally, concatenate all parts in reverse order.
// Time: O(n) - two passes
// Space: O(n) - stack
// 09/04/2018
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] ps = path.split("/");
        for (String p : ps) {
            if (p.equals("") || p.equals(".")) {
                continue;
            } else if (p.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(p);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}