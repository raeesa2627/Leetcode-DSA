class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0, maxIdx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);
        int fromFront = j + 1;
        int fromBack = n - i;
        int fromBoth = (i + 1) + (n - j);
        return Math.min(fromFront, Math.min(fromBack, fromBoth));
    }
}