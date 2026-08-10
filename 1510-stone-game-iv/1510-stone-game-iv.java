class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        // dp[i] = true if the player to move with i stones wins

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k * k <= i; k++) {
                // If removing k*k stones leaves the opponent in a losing state, current player wins
                if (!dp[i - k * k]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}