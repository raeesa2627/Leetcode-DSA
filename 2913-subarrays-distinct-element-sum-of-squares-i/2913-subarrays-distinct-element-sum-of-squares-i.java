class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        long ans = 0;
        final long MOD = 1_000_000_007L;

        // For every subarray, count distinct elements and add its square.
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();

            for (int j = i; j < n; j++) {
                set.add(nums.get(j));
                long distinct = set.size();
                ans = (ans + distinct * distinct) % MOD;
            }
        }

        return (int) ans;
    }
}