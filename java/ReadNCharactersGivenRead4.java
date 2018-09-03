/*
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * 
 * Note:
 * The read function will only be called once for each test case. 
 */

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

// Solution 1: 
// version 2:
// 09/03/2018
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int res = 0;
        int read = 4;
        while (read == 4 && res < n) {
            char[] temp = new char[4];
            read = read4(temp);
            for (int i = 0; i < read && res < n; i++) {
                buf[res++] = temp[i];
            }
        } 
        return res;
    }
}

// version 1:
// reference: https://discuss.leetcode.com/topic/18289/another-accepted-java-solution
// 09/24/2017
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        boolean eof = false;  // a flag indicating eof
        int count = 0;
        char[] newbuf = new char[4];
        
        while (n - count > 0 && !eof) {
            int newRead = read4(newbuf);
            if (newRead < 4) {  // reach end of file
                eof = true;
            }
            int toWrite = Math.min(n-count, newRead); // get the # of chars to write
            for (int i = 0; i < toWrite; i++) { // write the chars to buffer
                buf[count] = newbuf[i];
                count++;
            }
        }
        return count;
    }
}