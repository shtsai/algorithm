/*
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * 
 * Note:
 * The read function may be called multiple times. 
 */

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

// Solution 1:
// Use global variables to store info about last read and last read size.
// reference: https://discuss.leetcode.com/topic/7094/a-simple-java-code
// 2017/09/24
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    private char[] temp = new char[4];
    private int lastRead = 0;
    private int lastReadSize = 0;
    
    public int read(char[] buf, int n) {
        int count = 0;
        
        while (count < n) {
            int newRead;
            if (lastRead == lastReadSize) { // already read all chars in temp
                newRead = read4(temp);
                lastRead = 0;
                lastReadSize = newRead;
            } else {
                newRead = lastReadSize - lastRead;
            }
            
            if (newRead == 0) break;    // eof
            
            int toWrite = Math.min(n-count, newRead);
            for (int i = 0; i < toWrite; i++) {
                buf[count] = temp[lastRead];
                count++;
                lastRead++;
            }
        }
        
        return count;
    }
}