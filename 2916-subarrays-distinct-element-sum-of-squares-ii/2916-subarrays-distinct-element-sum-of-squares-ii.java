import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;

    long[] sum;
    long[] square;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;

        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        HashMap<Integer, Integer> last = new HashMap<>();

        long ans = 0;

        for (int r = 0; r < n; r++) {
            int prev = last.getOrDefault(nums[r], -1);

            // For all starting positions l in [prev + 1, r],
            // nums[r] is a new distinct element.
            rangeAdd(1, 0, n - 1, prev + 1, r, 1);

            // Sum of distinctCount^2 for every subarray ending at r
            ans = (ans + square[1]) % MOD;

            last.put(nums[r], r);
        }

        return (int) ans;
    }

    // Add val to every element in [ql, qr]
    private void rangeAdd(int node, int left, int right,
                          int ql, int qr, long val) {

        if (ql > right || qr < left) {
            return;
        }

        if (ql <= left && right <= qr) {
            apply(node, left, right, val);
            return;
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;

        rangeAdd(node * 2, left, mid, ql, qr, val);
        rangeAdd(node * 2 + 1, mid + 1, right, ql, qr, val);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        square[node] = (square[node * 2] + square[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int left, int right, long val) {
        long len = right - left + 1;

        // New square:
        // (x + val)^2 = x^2 + 2*val*x + val^2
        square[node] = (
            square[node]
            + 2L * val % MOD * sum[node] % MOD
            + val * val % MOD * (len % MOD) % MOD
        ) % MOD;

        sum[node] = (sum[node] + val * (len % MOD)) % MOD;

        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }
}