class Solution {
    public void solve(List<String> ans, String s, int open, int close, int n) {
        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }
        if (open < n) {
            solve(ans, s + "(", open + 1, close, n);
        }
        if (close < open) {
            solve(ans, s + ")", open, close + 1, n);
        }
    }
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        solve(ans, "", 0, 0, n);
        return ans;
    }
}