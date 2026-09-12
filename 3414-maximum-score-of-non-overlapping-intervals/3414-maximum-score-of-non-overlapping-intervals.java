class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[] L = new int[n], R = new int[n], W = new int[n], origIdx = new int[n];
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;

        Arrays.sort(order, (a, b) -> {
            int ra = intervals.get(a).get(1), rb = intervals.get(b).get(1);
            if (ra != rb) return Integer.compare(ra, rb);
            int la = intervals.get(a).get(0), lb = intervals.get(b).get(0);
            if (la != lb) return Integer.compare(la, lb);
            return Integer.compare(a, b);
        });

        for (int i = 0; i < n; i++) {
            int o = order[i];
            List<Integer> iv = intervals.get(o);
            L[i] = iv.get(0);
            R[i] = iv.get(1);
            W[i] = iv.get(2);
            origIdx[i] = o;
        }

        // p[k] = count of intervals among the first k sorted intervals with R < L[k]
        int[] p = new int[n];
        for (int k = 0; k < n; k++) p[k] = lowerBound(R, 0, k, L[k]);

        final int K = 4;
        final long NEG = Long.MIN_VALUE;
        long[][] sum = new long[n + 1][K + 1];
        int[][][] list = new int[n + 1][K + 1][];
        for (long[] row : sum) Arrays.fill(row, NEG);
        sum[0][0] = 0;
        list[0][0] = new int[0];

        for (int i = 1; i <= n; i++) {
            int cur = i - 1;
            for (int j = 0; j <= K; j++) {
                long bestSum = sum[i - 1][j];
                int[] bestList = list[i - 1][j];

                if (j >= 1) {
                    int pp = p[cur];
                    long prevSum = sum[pp][j - 1];
                    if (prevSum != NEG) {
                        long takeSum = prevSum + W[cur];
                        int[] takeList = insertSorted(list[pp][j - 1], origIdx[cur]);
                        if (bestSum == NEG || takeSum > bestSum ||
                                (takeSum == bestSum && lexLess(takeList, bestList))) {
                            bestSum = takeSum;
                            bestList = takeList;
                        }
                    }
                }
                sum[i][j] = bestSum;
                list[i][j] = bestList;
            }
        }

        long bestOverallSum = NEG;
        int[] bestOverallList = new int[0];
        for (int j = 0; j <= K; j++) {
            long s = sum[n][j];
            if (s == NEG) continue;
            int[] l = list[n][j];
            if (bestOverallSum == NEG || s > bestOverallSum ||
                    (s == bestOverallSum && lexLess(l, bestOverallList))) {
                bestOverallSum = s;
                bestOverallList = l;
            }
        }
        return bestOverallList;
    }

    private int lowerBound(int[] arr, int lo, int hi, long target) {
        int l = lo, h = hi;
        while (l < h) {
            int mid = (l + h) >>> 1;
            if (arr[mid] < target) l = mid + 1; else h = mid;
        }
        return l;
    }

    private int[] insertSorted(int[] arr, int val) {
        int n = arr.length;
        int[] res = new int[n + 1];
        int pos = 0;
        while (pos < n && arr[pos] < val) { res[pos] = arr[pos]; pos++; }
        res[pos] = val;
        for (int k = pos; k < n; k++) res[k + 1] = arr[k];
        return res;
    }

    private boolean lexLess(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) return a[i] < b[i];
        }
        return a.length < b.length;
    }
}