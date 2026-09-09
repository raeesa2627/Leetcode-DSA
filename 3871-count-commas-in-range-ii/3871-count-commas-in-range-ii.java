class Solution {
    public long countCommas(long n) {
        long total = 0;
        long low = 1L, high = 9L;
        int d = 1;

        while (low <= n) {
            long upper = Math.min(high, n);
            long count = upper - low + 1;
            long commasPerNumber = (d - 1) / 3;
            total += count * commasPerNumber;

            d++;
            low = high + 1;
            high = high * 10 + 9;
        }

        return total;
    }
}