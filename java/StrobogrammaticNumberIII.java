/*
    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

    Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

    For example,
    Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

    Note:
    Because the range might be a large number, the low and high numbers are represented as string.
 */

// Solution 1 version 2:
// Based on Strobogrammatic Number II
// Generate all strobogrammatic Number with length between
// low.length() and high.length(), then count the number of 
// valid ones.
// Time: O(m*(m-n))=O(m^2) - m:high.length, n:low.length
// Space: O(m^2)
// 10/08/2017

class Solution {
    String[][] pairs = {{"1","1"},{"6","9"},{"9","6"},{"8","8"}};
    public int strobogrammaticInRange(String low, String high) {
        List<String> nums = new ArrayList<>();
        
        for (int len = low.length(); len <= high.length(); len++) {
            nums.addAll(helper(len, len));
        }
        
        int count = 0;
        for (String s : nums) {
            if (s.length() == low.length() && s.compareTo(low) < 0) {
                continue;
            }
            if (s.length() == high.length() && s.compareTo(high) > 0) {
                continue;
            }
            count++;
        }
        return count;
    }
    
    private List<String> helper(int cur, int max) {
        List<String> res = new ArrayList<>();
        if (cur == 0) {
            res.add("");
            return res;
        }
        if (cur == 1) {
            res.add("0");
            res.add("1");
            res.add("8");
            return res;
        }
        List<String> sublist = helper(cur-2, max);
        for (String sub : sublist) {
            if (cur != max) {
                res.add("0" + sub + "0");
            }
            for (String[] pair : pairs) {
                res.add(pair[0] + sub + pair[1]);
            }
        }
        return res;
    }
}

// Solution 1:
// Only check strobogrammatic numbers whose length is the same 
// as low or high.
// 12/26/2017

class Solution {
    public int strobogrammaticInRange(String low, String high) {
        if (low.length() > high.length()) return 0;
        int count = 0;
        if (low.length() == high.length()) {
            if (low.compareTo(high) > 0) return 0;
            List<String> ls = strobogrammaticGenerate(low.length());
            for (String lss : ls) {
                if (low.compareTo(lss) <= 0 && high.compareTo(lss) >= 0) {
                    count++;
                }
            }
            return count;
        }
        List<String> ls = strobogrammaticGenerate(low.length());
        for (String lss : ls) {
            if (low.compareTo(lss) <= 0) {
                count++;
            }
        }
        List<String> hs = strobogrammaticGenerate(high.length());
        for (String hss : hs) {
            if (high.compareTo(hss) >= 0) {
                count++;
            }
        }
        for (int i = low.length() + 1; i < high.length(); i++) {
            List<String> s = strobogrammaticGenerate(i);
            count += s.size();
        }
        return count;
    }
    
    final String[] center = {"0", "1", "8"};
    final String[][] pairs = {{"1", "1"},
                             {"6", "9"},
                             {"8", "8"},
                             {"9", "6"}};
    
    public List<String> strobogrammaticGenerate(int n) {
        List<String> res = new ArrayList<>();
        find(res, n, "");
        return res;
    }
    
    public void find(List<String> res, int len, String cur) {
        if (cur.length() == len) {
            res.add(cur);
            return;
        }
        if (cur.length() == 0 && (len % 2 == 1)) {
            for (String c : center) {
                find(res, len, c);
            }
            return;
        }
        for (String[] p : pairs) {
            find(res, len, p[0] + cur + p[1]);
        }
        if (len - cur.length() != 2) {
            find(res, len, "0" + cur + "0");
        }
    }
}