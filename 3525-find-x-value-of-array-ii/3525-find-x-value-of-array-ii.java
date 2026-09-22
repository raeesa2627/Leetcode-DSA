class Solution {

    static class Node {
        // cnt[r] = number of non-empty prefixes
        // whose product % k == r
        int[] cnt;

        // Product of the entire segment modulo k
        int product;

        Node(int k) {
            cnt = new int[k];
            product = 1;
        }
    }

    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // Get information for nums[start ... n-1]
            Node result = query(1, 0, n - 1, start, n - 1);

            ans[q] = result.cnt[x];
        }

        return ans;
    }

    // Build segment tree
    private void build(int node, int left, int right, int[] nums) {
        if (left == right) {
            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Point update
    private void update(int node, int left, int right,
                        int index, int value) {

        if (left == right) {
            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Query range [ql, qr]
    private Node query(int node, int left, int right,
                       int ql, int qr) {

        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node a = query(node * 2, left, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(a, b);
    }

    // Merge two consecutive segments:
    // A followed by B
    private Node merge(Node a, Node b) {
        Node res = new Node(k);

        /*
         * Prefixes of A remain unchanged.
         */
        for (int r = 0; r < k; r++) {
            res.cnt[r] += a.cnt[r];
        }

        /*
         * A prefix that enters B has:
         *
         * product(A) * product(prefix of B)
         *
         * modulo k.
         */
        for (int r = 0; r < k; r++) {
            int count = b.cnt[r];

            if (count == 0) {
                continue;
            }

            int newRem = (a.product * r) % k;
            res.cnt[newRem] += count;
        }

        // Product of entire A + B
        res.product = (a.product * b.product) % k;

        return res;
    }
}