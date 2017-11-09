/*
  Given a string "123456789", add +/- to get target value.

  A function evaluate() is given to you
  evaluate('3+23-2') = 24
  
  input = '123456789'

  give me all combinations of input (with +/- inserted) such that evaluate gives me 100

  evaluate will return me 100

  '1+2+3-4+5+6+78+9' = 100

  '-1+2-3+4+5+6+78+9' = 100 (still valid)

  only +/- (no x or /)

  output= array of strings of evaluate operations
  ['1+2+3-4+5+6+78+9', '-1+2-3+4+5+6+78+9', etc...]

  FB - phone interview
 */
  
/* 
    Testing

  12345
  
  start =0
  
  +1     + 2   +23   + 234
  -1
  
  +12
  -12
  
  +123
  -123
  
  +1234
  -1234
  
  +12345
  -12345
 */

public List<String> findCombinations(String input, int target) {
     List<String> res = new ArrayList<>();
     dfs(input, res, 0, "", target);
     return res;
}

private void dfs(String input, List<String> res, int start, String current, int target) {
     if (start == input.length()) {
        if (evaluate(current) == target) {
             res.add(curS);
        }
        return;
     }
  
     for (int i = start+1; i <= input.length(); i++) {
        dfs(input, res, i, current + "+" + input.substring(start, i) + target);
        dfs(input, res, i, current + "-" + input.substring(start, i) + target);
     }
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  