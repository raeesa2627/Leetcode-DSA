class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;

        int oddCnt = 0;
        for (int i = 0; i < 26; i++) if (count[i] % 2 != 0) oddCnt++;

        boolean hasMid = (n % 2 == 1);
        if (hasMid && oddCnt != 1) return "";
        if (!hasMid && oddCnt != 0) return "";

        int half = n / 2;
        char midChar = 0;
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCounts[i] = count[i] / 2;
            if (count[i] % 2 == 1) midChar = (char) ('a' + i);
        }

        int[] targetHalfCounts = new int[26];
        for (int i = 0; i < half; i++) targetHalfCounts[target.charAt(i) - 'a']++;

        boolean fullMatchFeasible = true;
        for (int i = 0; i < 26; i++) {
            if (targetHalfCounts[i] != halfCounts[i]) { fullMatchFeasible = false; break; }
        }

        if (fullMatchFeasible) {
            String firstHalf = target.substring(0, half);
            StringBuilder sb = new StringBuilder();
            sb.append(firstHalf);
            if (hasMid) sb.append(midChar);
            sb.append(new StringBuilder(firstHalf).reverse());
            String T = sb.toString();
            if (T.compareTo(target) > 0) return T;
        }

        int[] remaining = halfCounts.clone();
        int bestJ = -1;
        char bestDivChar = 0;
        int[] bestRemainingAfter = null;
        String bestPrefix = "";

        for (int j = 0; j < half; j++) {
            char tc = target.charAt(j);
            for (int c = tc - 'a' + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    bestJ = j;
                    bestDivChar = (char) ('a' + c);
                    bestPrefix = target.substring(0, j);
                    int[] afterRemove = remaining.clone();
                    afterRemove[c]--;
                    bestRemainingAfter = afterRemove;
                    break;
                }
            }
            int tcIdx = tc - 'a';
            if (remaining[tcIdx] > 0) {
                remaining[tcIdx]--;
            } else {
                break;
            }
        }

        if (bestJ == -1) return "";

        StringBuilder aBuilder = new StringBuilder();
        aBuilder.append(bestPrefix);
        aBuilder.append(bestDivChar);
        for (int c = 0; c < 26; c++) {
            for (int k = 0; k < bestRemainingAfter[c]; k++) {
                aBuilder.append((char) ('a' + c));
            }
        }
        String A = aBuilder.toString();
        StringBuilder result = new StringBuilder();
        result.append(A);
        if (hasMid) result.append(midChar);
        result.append(new StringBuilder(A).reverse());

        return result.toString();
    }
}