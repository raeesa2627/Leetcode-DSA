class Solution {
    int n;
    int[] len, pre, suf, best;
    char[] leftCh, rightCh;
    char[] arr;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        arr = s.toCharArray();
        n = arr.length;
        int size = 4 * n;
        len = new int[size];
        pre = new int[size];
        suf = new int[size];
        best = new int[size];
        leftCh = new char[size];
        rightCh = new char[size];

        build(1, 0, n - 1);

        int k = queryCharacters.length();
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            arr[idx] = queryCharacters.charAt(i);
            update(1, 0, n - 1, idx);
            ans[i] = best[1];
        }
        return ans;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            len[node] = pre[node] = suf[node] = best[node] = 1;
            leftCh[node] = rightCh[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        pull(node, mid);
    }

    private void update(int node, int l, int r, int idx) {
        if (l == r) {
            leftCh[node] = rightCh[node] = arr[idx];
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx);
        else update(2 * node + 1, mid + 1, r, idx);
        pull(node, mid);
    }

    private void pull(int node, int mid) {
        int lc = 2 * node, rc = 2 * node + 1;
        len[node] = len[lc] + len[rc];
        leftCh[node] = leftCh[lc];
        rightCh[node] = rightCh[rc];

        pre[node] = pre[lc];
        if (pre[lc] == len[lc] && rightCh[lc] == leftCh[rc]) pre[node] += pre[rc];

        suf[node] = suf[rc];
        if (suf[rc] == len[rc] && rightCh[lc] == leftCh[rc]) suf[node] += suf[lc];

        best[node] = Math.max(best[lc], best[rc]);
        if (rightCh[lc] == leftCh[rc]) best[node] = Math.max(best[node], suf[lc] + pre[rc]);
    }
}