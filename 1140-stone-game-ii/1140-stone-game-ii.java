class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        Integer[][] memo = new Integer[n][2 * n + 1];
        return dp(0, 1, suffixSum, memo, n);
    }
    
    private int dp(int i, int M, int[] suffixSum, Integer[][] memo, int n) {
        if (i >= n) return 0;
        if (i + 2 * M >= n) {
            return suffixSum[i]; // take everything left
        }
        if (memo[i][M] != null) return memo[i][M];
        
        int best = 0;
        for (int X = 1; X <= 2 * M; X++) {
            if (i + X > n) break;
            int nextM = Math.max(M, X);
            int gain = suffixSum[i] - dp(i + X, nextM, suffixSum, memo, n);
            best = Math.max(best, gain);
        }
        
        memo[i][M] = best;
        return best;
    }
}