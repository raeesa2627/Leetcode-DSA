class Solution {
    static final int MOD = 1_000_000_007;
    public int numberOfSets(int n, int k) {
        int N = n + k - 1;

        // precompute factorials up to N
        long[] fact = new long[N + 1];
        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        long numerator = fact[N];
        long denom = fact[2 * k] * fact[N - 2 * k] % MOD;

        return (int) (numerator * modInverse(denom) % MOD);
    }

    private long modInverse(long a) {
        return modPow(a, MOD - 2);
    }

    private long modPow(long base, long exp) {
        base %= MOD;
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % MOD;
            base = base * base % MOD;
            exp >>= 1;
        }
        return result;
    }
}