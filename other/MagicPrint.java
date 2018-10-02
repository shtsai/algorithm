/* 
    Magic print

    n = 1
    x

    n = 2
    xxx
    x x
    xxx

    n = 3
    xxxxxxxxx
    x xx xx x
    xxxxxxxxx
    xxx   xxx
    x x   x x
    xxx   xxx
    xxxxxxxxx
    x xx xx x
    xxxxxxxxx

    n = 4
    xxxxxxxxxxxxxxxxxxxxxxxxxxx
    x xx xx xx xx xx xx xx xx x
    xxxxxxxxxxxxxxxxxxxxxxxxxxx
    xxx   xxxxxx   xxxxxx   xxx
    x x   x xx x   x xx x   x x
    xxx   xxxxxx   xxxxxx   xxx
    xxxxxxxxxxxxxxxxxxxxxxxxxxx
    x xx xx xx xx xx xx xx xx x
    xxxxxxxxxxxxxxxxxxxxxxxxxxx
    xxxxxxxxx         xxxxxxxxx
    x xx xx x         x xx xx x
    xxxxxxxxx         xxxxxxxxx
    xxx   xxx         xxx   xxx
    x x   x x         x x   x x
    xxx   xxx         xxx   xxx
    xxxxxxxxx         xxxxxxxxx
    x xx xx x         x xx xx x
    xxxxxxxxx         xxxxxxxxx
    xxxxxxxxxxxxxxxxxxxxxxxxxxx
    x xx xx xx xx xx xx xx xx x
    xxxxxxxxxxxxxxxxxxxxxxxxxxx
    xxx   xxxxxx   xxxxxx   xxx
    x x   x xx x   x xx x   x x
    xxx   xxxxxx   xxxxxx   xxx
    xxxxxxxxxxxxxxxxxxxxxxxxxxx
    x xx xx xx xx xx xx xx xx x
    xxxxxxxxxxxxxxxxxxxxxxxxxxx

 */


public class Main {
    public static void main(String[] args) {
        List<List<Character>> list = draw(4);
        for (List<Character> l : list) {
            for (char c : l) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    
    public static List<List<Character>> draw(int n) {
        return helper(n);
    }
    
    private static List<List<Character>> helper(int n) {
        List<List<Character>> res = new ArrayList<>();
        if (n == 1) {
            res.add(new ArrayList<>());
            res.get(0).add('x');
            return res;
        }
        for (int i = 0; i < (int) Math.pow(3, n - 1); i++) {
            res.add(new ArrayList<>());
        }
        
        int offset = (int) Math.pow(3, n - 2);
        List<List<Character>> sub = helper(n - 1);
        List<List<Character>> white = addWhite(offset);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    add(res, white, i * offset);
                } else {
                    add(res, sub, i * offset);
                }
            }
        }
        return res;
    }
    
    private static void add(List<List<Character>> res, List<List<Character>> toAdd, int row) {
        for (int i = 0; i < toAdd.size(); i++) {
            for (int j = 0; j < toAdd.get(i).size(); j++) {
                res.get(i + row).add(toAdd.get(i).get(j));
            }
        }
    }
    
    private static List<List<Character>> addWhite(int n) {
        List<List<Character>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                res.get(i).add(' ');
            }
        }
        return res;
    }
}