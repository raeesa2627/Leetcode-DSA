class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int oddCount = 0;
        for (int x : nums1) {
            if (x % 2 != 0) oddCount++;
        }
        boolean canBeOdd = (oddCount >= 1);
        boolean canBeEven = (oddCount == 0 || oddCount >= 2);
        return canBeOdd || canBeEven;
    }
}