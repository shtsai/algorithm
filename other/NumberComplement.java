/*
    Given a number, return its complement in binary form.

    For example:
        5 -> 101 (in binary)
        flip its bits to get its complement
        010 -> 2

    // Cloudera
    // 09/25/2017
 */
 
class NumberComplement {
    public int getIntegerComplement(int n) {
        int copy = n;
        int highest = 0;
        while (copy > 0) {  // find highest bit
            copy = copy >> 1;
            highest++;
        }
        
        int mask = 1;
        while (highest > 0) {
            n = n ^ mask;      // flip the bit
            mask = mask << 1;  // move to mask to left by one unit
            highest--;
        }
        
        return n;
    }
}
