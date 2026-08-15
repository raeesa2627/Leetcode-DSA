class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        for (int i = 0; i < n; i++) {
            totalXor = totalXor ^ nums[i];
        }
        boolean hasNonZero = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                hasNonZero = true;
                break;
            }
        }
        if (hasNonZero == false) {
            return 0;
        }
        if (totalXor != 0) {
            return n;
        }
        return n - 1;
    }
}