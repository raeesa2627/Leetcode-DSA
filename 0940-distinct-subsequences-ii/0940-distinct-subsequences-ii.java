class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        int[] dp = new int[26]; // dp[c] = number of distinct subsequences ending in char c

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            long total = 0;
            for (int j = 0; j < 26; j++) {
                total += dp[j];
            }
            total %= MOD;

            long newVal = (total + 1) % MOD;
            dp[idx] = (int) newVal;
        }

        long ans = 0;
        for (int j = 0; j < 26; j++) {
            ans += dp[j];
        }
        return (int) (ans % MOD);
    }
}