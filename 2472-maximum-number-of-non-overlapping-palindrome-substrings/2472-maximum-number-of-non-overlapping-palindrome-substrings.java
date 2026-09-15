class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        if (n < k) return 0;

        // isPal[i][j] = true if s[i..j] (inclusive) is a palindrome
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
            }
        }

        // dp[i] = max number of non-overlapping valid palindromic substrings
        // using the prefix s[0..i-1]
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; // option: don't end a palindrome exactly at i

            // Try the shortest possible palindrome ending at index i-1: length k
            if (i >= k) {
                int start = i - k; // s[start..i-1], length k
                if (isPal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }

            // Also try length k+1 (needed to cover both parities of palindrome centers)
            if (i >= k + 1) {
                int start = i - k - 1; // s[start..i-1], length k+1
                if (isPal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
        }

        return dp[n];
    }
}