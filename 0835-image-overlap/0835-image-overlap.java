class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> onesA = new ArrayList<>();
        List<int[]> onesB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) onesA.add(new int[]{i, j});
                if (img2[i][j] == 1) onesB.add(new int[]{i, j});
            }
        }

        if (onesA.isEmpty() || onesB.isEmpty()) return 0;

        Map<Integer, Integer> count = new HashMap<>();
        int best = 0;

        for (int[] a : onesA) {
            for (int[] b : onesB) {
                int dx = a[0] - b[0];
                int dy = a[1] - b[1];
                // encode (dx, dy) into a single key; offset by n to keep values non-negative
                int key = (dx + n) * 200 + (dy + n);
                int c = count.getOrDefault(key, 0) + 1;
                count.put(key, c);
                best = Math.max(best, c);
            }
        }

        return best;
    }
}