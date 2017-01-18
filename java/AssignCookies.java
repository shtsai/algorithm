/*
 * Assume you are an awesome parent and want to give your children some cookies. 
 * But, you should give each child at most one cookie. Each child i has a greed factor gi, 
 * which is the minimum size of a cookie that the child will be content with; 
 * and each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, 
 * and the child i will be content. 
 * Your goal is to maximize the number of your content children and output the maximum number.
 *
 * Note:
 * You may assume the greed factor is always positive. 
 * You cannot assign more than one cookie to one child.
 */

// greedy
public class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // first sort two arrays
        Arrays.sort(g);  
        Arrays.sort(s);
        
        int gp = 0; // g pointer
        int sp = 0; // s pointer
        int count = 0; 
        while (gp < g.length && sp < s.length) {
            if (g[gp] <= s[sp]) { // if find a cookie large enough for the current child
                gp++;
                sp++;
                count++;
            } else { // else, move on to next larger cookie
                sp++;
            }
        }
        return count;
    }
}