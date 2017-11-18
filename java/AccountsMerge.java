/*
    Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

    Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

    After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

    Example 1:
    Input: 
    accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
    Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    Explanation: 
    The first and third John's are the same person as they have the common email "johnsmith@mail.com".
    The second John and Mary are different people as none of their email addresses are used by other accounts.
    We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
    ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
    Note:

    The length of accounts will be in the range [1, 1000].
    The length of accounts[i] will be in the range [1, 10].
    The length of accounts[i][j] will be in the range [1, 30].
 */

// Solution 1: Union Find
// Union all emails belonged to the same user.
// This way, we can union all emails belong to the same user.
// Then reconstruct the account lists.
// Sort the lists, and add owner name to the front.
// 
// Time: O(n) - n is the total number of emails
// Space: O(n) - three HashMap
// 11/17/2017

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, String> root = new HashMap<>();
        HashMap<String, List<String>> res = new HashMap<>();
        HashMap<String, String> owner = new HashMap<>();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                if (!root.containsKey(account.get(i))) {
                    root.put(account.get(i), account.get(i));
                    owner.put(account.get(i), account.get(0));
                }
            }
            for (int i = 1; i < account.size() - 1; i++) {
                if (i + 1 < account.size()) {
                    union(root, account.get(i), account.get(i+1));
                }
            }
        }
        for (String email : root.keySet()) {
            String parent = find(root, email);
            if (!res.containsKey(parent)) {
                res.put(parent, new ArrayList<>());
            }
            res.get(parent).add(email);
        }
        List<List<String>> list = new ArrayList<>();
        for (List<String> l : res.values()) {
            Collections.sort(l);
            l.add(0, owner.get(l.get(0)));
            list.add(l);
        }
        for (String s : root.keySet()) {
            System.out.println(s + " " + find(root, s));
        }
        return list;
    }
    
    private String find(HashMap<String, String> root, String a) {
        if (root.get(a) == a) return a;
        String r = find(root, root.get(a));
        root.put(a, r);
        return r;
    }
    
    private void union(HashMap<String, String> root, String a, String b) {
        String ar = find(root, a);
        String br = find(root, b);
        if (!ar.equals(br)) {
            root.put(ar, br);
        }
    }
}