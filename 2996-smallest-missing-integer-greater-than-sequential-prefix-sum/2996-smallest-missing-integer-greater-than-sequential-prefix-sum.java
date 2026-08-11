class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        int prefixLength = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixLength++;
            } else {
                break;
            }
        }
        int sum = 0;
        for (int i = 0; i < prefixLength; i++) {
            sum = sum + nums[i];
        }
        boolean found = true;
        while (found) {
            found = false;
            for (int i = 0; i < n; i++) {
                if (nums[i] == sum) {
                    found = true;
                    break;
                }
            }
            if (found) {
                sum++;
            }
        }
        return sum;
    }
}