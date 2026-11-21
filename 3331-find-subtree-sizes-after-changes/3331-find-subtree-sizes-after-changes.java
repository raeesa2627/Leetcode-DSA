import java.util.*;
class Solution {
    List<Integer>[] tree;
    int[] ans;
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        // Build the original tree
        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Find the closest ancestor with the same character
        dfs(0, s, last, newParent);

        // Build the modified tree
        for (int i = 0; i < n; i++) {
            tree[i].clear();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        ans = new int[n];
        calculateSize(0);

        return ans;
    }

    void dfs(int node, String s, int[] last, int[] newParent) {
        int c = s.charAt(node) - 'a';

        int old = last[c];

        if (old != -1) {
            newParent[node] = old;
        }

        last[c] = node;

        for (int child : tree[node]) {
            dfs(child, s, last, newParent);
        }

        last[c] = old;
    }

    int calculateSize(int node) {
        ans[node] = 1;

        for (int child : tree[node]) {
            ans[node] += calculateSize(child);
        }

        return ans[node];
    }
}