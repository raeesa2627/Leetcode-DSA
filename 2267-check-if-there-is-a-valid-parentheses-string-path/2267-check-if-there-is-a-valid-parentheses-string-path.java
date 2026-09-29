class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even for a valid parentheses string.
        int len = m + n - 1;
        if (len % 2 == 1) {
            return false;
        }

        // dp[j][balance] = possible for the previous/current row.
        boolean[][] dp = new boolean[n][len + 1];

        if (grid[0][0] == ')') {
            return false;
        }

        dp[0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                boolean[] cur = new boolean[len + 1];
                int change = (grid[i][j] == '(') ? 1 : -1;

                for (int balance = 0; balance <= len; balance++) {

                    boolean reachable = false;

                    // From top
                    if (i > 0 && dp[j][balance]) {
                        reachable = true;
                    }

                    // From left
                    if (j > 0 && dp[j - 1][balance]) {
                        reachable = true;
                    }

                    if (reachable) {
                        int newBalance = balance + change;

                        if (newBalance >= 0 && newBalance <= len) {
                            cur[newBalance] = true;
                        }
                    }
                }

                dp[j] = cur;
            }
        }

        return dp[n - 1][0];
    }
}