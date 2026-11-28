import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count eligible free-item types for each factor.
        int[] divisibleCount = new int[n + 1];

        for (int f = 1; f <= n; f++) {
            for (int j = f; j <= n; j += f) {
                divisibleCount[f] += freq[j];
            }
        }

        // Sort items by price.
        long[] order = new long[n];

        for (int i = 0; i < n; i++) {
            order[i] = ((long) items[i][1] << 32) | i;
        }

        Arrays.sort(order);

        long remaining = budget;
        long answer = 0;

        for (int k = 0; k < n; k++) {
            int index = (int) order[k];

            int factor = items[index][0];
            int price = items[index][1];

            // A bonus copy is not worthwhile at this price.
            if (price >= 2L * minPrice) {
                break;
            }

            long bonusCopies = divisibleCount[factor] - 1L;

            long canBuy = Math.min(bonusCopies, remaining / price);

            answer += 2L * canBuy;
            remaining -= canBuy * price;
        }

        // Use the remaining budget for the cheapest items.
        answer += remaining / minPrice;

        return (int) answer;
    }
}