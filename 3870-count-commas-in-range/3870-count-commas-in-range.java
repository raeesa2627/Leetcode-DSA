class Solution {
    public int countCommas(int n) {
        int total = 0;
        for (int i = 1; i <= n; i++) {
            int digits = String.valueOf(i).length();
            total += (digits - 1) / 3;
        }
        return total;
    }
}