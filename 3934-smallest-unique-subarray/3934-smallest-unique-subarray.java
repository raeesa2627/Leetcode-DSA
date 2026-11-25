import java.util.*;
class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] sa = buildSuffixArray(nums);
        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        
        int k = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) {
                k = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + k < n && j + k < n &&
                   nums[i + k] == nums[j + k]) {
                k++;
            }

            lcp[r] = k;

            if (k > 0) k--;
        }

        int ans = n;

        for (int r = 0; r < n; r++) {
            int maxLcp = lcp[r];

            if (r + 1 < n) {
                maxLcp = Math.max(maxLcp, lcp[r + 1]);
            }

            int len = maxLcp + 1;

            if (len <= n - sa[r]) {
                ans = Math.min(ans, len);
            }
        }

        return ans;
    }

    private int[] buildSuffixArray(int[] nums) {
        int n = nums.length;
        int N = n + 1;

        int[] p = new int[N];
        int[] c = new int[N];
        int[] cnt = new int[Math.max(N, Arrays.stream(nums).max().getAsInt() + 2)];

        for (int i = 0; i < N; i++) {
            int key = (i == n) ? 0 : nums[i] + 1;
            cnt[key]++;
        }

        for (int i = 1; i < cnt.length; i++) {
            cnt[i] += cnt[i - 1];
        }

        for (int i = N - 1; i >= 0; i--) {
            int key = (i == n) ? 0 : nums[i] + 1;
            p[--cnt[key]] = i;
        }

        int classes = 1;
        c[p[0]] = 0;

        for (int i = 1; i < N; i++) {
            int a = (p[i] == n) ? 0 : nums[p[i]] + 1;
            int b = (p[i - 1] == n) ? 0 : nums[p[i - 1]] + 1;

            if (a != b) classes++;
            c[p[i]] = classes - 1;
        }

        int[] pn = new int[N];
        int[] cn = new int[N];

        for (int len = 1; len < N; len <<= 1) {
            for (int i = 0; i < N; i++) {
                pn[i] = p[i] - len;
                if (pn[i] < 0) pn[i] += N;
            }

            cnt = new int[classes];

            for (int i = 0; i < N; i++) {
                cnt[c[pn[i]]]++;
            }

            for (int i = 1; i < classes; i++) {
                cnt[i] += cnt[i - 1];
            }

            for (int i = N - 1; i >= 0; i--) {
                int x = pn[i];
                p[--cnt[c[x]]] = x;
            }

            cn[p[0]] = 0;
            int newClasses = 1;

            for (int i = 1; i < N; i++) {
                int cur = p[i];
                int prev = p[i - 1];

                int mid1 = (cur + len) % N;
                int mid2 = (prev + len) % N;

                if (c[cur] != c[prev] ||
                    c[mid1] != c[mid2]) {
                    newClasses++;
                }

                cn[cur] = newClasses - 1;
            }

            int[] temp = c;
            c = cn;
            cn = temp;

            classes = newClasses;

            if (classes == N) break;
        }

        return Arrays.copyOfRange(p, 1, N);
    }
    
}