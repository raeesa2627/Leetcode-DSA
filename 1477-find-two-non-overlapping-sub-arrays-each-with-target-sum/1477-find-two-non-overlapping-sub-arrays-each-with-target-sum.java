class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // best[i] = length of the shortest valid subarray ending at or before index i
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);

        int left = 0;
        long sum = 0;
        int ans = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            int prevBest = (right > 0) ? best[right - 1] : Integer.MAX_VALUE;
            best[right] = prevBest;

            if (sum == target) {
                int curLen = right - left + 1;

                // combine with best subarray ending before this one starts
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, curLen + best[left - 1]);
                }

                best[right] = Math.min(prevBest, curLen);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}