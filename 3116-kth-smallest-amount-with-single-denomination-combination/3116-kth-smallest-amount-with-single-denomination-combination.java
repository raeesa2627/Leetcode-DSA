class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int minCoin = Integer.MAX_VALUE;
        for (int c : coins) minCoin = Math.min(minCoin, c);
        long low = 1, high = (long) minCoin * k;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (countUpTo(coins, mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }   
        return low;
    }
    private long countUpTo(int[] coins, long x) {
        int n = coins.length;
        long count = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = Integer.bitCount(mask);
            boolean overflow = false;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcm = lcmOf(lcm, coins[i], x);
                    if (lcm > x) {
                        overflow = true;
                        break;
                    }
                }
            }
            if (overflow) continue; 
            long term = x / lcm;
            if (bits % 2 == 1) {
                count += term;
            } else {
                count -= term;
            }
        }   
        return count;
    }
    private long lcmOf(long a, long b, long limit) {
        long g = gcd(a, b);
        long result = a / g;
        if (result > limit / b) {
            return limit + 1; 
        }   
        return result * b;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}