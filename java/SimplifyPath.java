/*
 *  Given an absolute path for a file (Unix-style), simplify it.
 *  For example,
 *  path = "/home/", => "/home"
 *  path = "/a/./b/../../c/", => "/c"
 */

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