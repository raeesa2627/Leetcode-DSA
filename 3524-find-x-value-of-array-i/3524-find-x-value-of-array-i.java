class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k]; 
        for (int num : nums) {
            int c = num % k;
            long[] newDp = new long[k];
            for (int v0 = 0; v0 < k; v0++) {
                if (dp[v0] != 0) {
                    int v = (v0 * c) % k;
                    newDp[v] += dp[v0];
                }
            }
            newDp[c] += 1;
            dp = newDp;
            for (int v = 0; v < k; v++) {
                result[v] += dp[v];
            }
        }
        return result;
    }
}