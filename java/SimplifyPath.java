/*
 *  Given an absolute path for a file (Unix-style), simplify it.
 *  For example,
 *  path = "/home/", => "/home"
 *  path = "/a/./b/../../c/", => "/c"
 */

// Solution 2: List (Stack is not allowed)
// Same idea, but uses list.
// Time: O(n) - two passes
// Space: O(n) - list
// 11/09/2017
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
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

// version 2:
// 11/09/2017
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");
        
        for (String p : paths) {
            if (p.equals(".") || p.equals("")) {
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
        StringBuilder sb2 = new StringBuilder();
        while (!stack.isEmpty()) {  // concatenate in reverse order
            sb2.append("/" + stack.pop() + sb);
            sb = sb2;
            sb2 = new StringBuilder();
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }
}

// version 1:
public class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return null;
        }

        LinkedList<String> stack = new LinkedList<>();
        for (String directory : path.split("/")) {
            if (!stack.isEmpty() && directory.equals("..")) {   // '..' means parent directory, need to pop
                stack.pop();
            } else if (!directory.equals("") && !directory.equals(".") && !directory.equals("..")) {
                stack.push(directory);
            }
        }
        
        String result = "";
        for (String directory : stack) {
            result = "/" + directory + result;  // need to reverse order b/c stack is LIFO
        }
        if (result == "") {
            return "/";
        } else {
            return result;
        }
    }
}