import java.util.*;
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = 1;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    value[i]++;
                }
            }
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) ->
            Integer.compare(items[b][1], items[a][1])
        );

        int NEG = -1000000000;

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        int ans = 0;
        int idx = 0;

        while (idx < n) {
            int price = items[order[idx]][1];

            int[] has = new int[budget + 1];
            Arrays.fill(has, NEG);

            while (idx < n && items[order[idx]][1] == price) {
                int id = order[idx];
                int val = value[id];

                for (int c = budget; c >= price; c--) {
                    if (dp[c - price] != NEG) {
                        has[c] = Math.max(
                            has[c],
                            dp[c - price] + val
                        );

                        dp[c] = Math.max(
                            dp[c],
                            dp[c - price] + val
                        );
                    }
                }

                idx++;
            }

            for (int c = 0; c <= budget; c++) {
                if (has[c] != NEG) {
                    ans = Math.max(
                        ans,
                        has[c] + (budget - c) / price
                    );
                }
            }
        }

        return ans;
    }
}