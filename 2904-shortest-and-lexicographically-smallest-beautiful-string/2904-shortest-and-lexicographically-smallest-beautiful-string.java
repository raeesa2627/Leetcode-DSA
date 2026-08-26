class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int ones = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') ones++;
                if (ones == k) {
                    int len = j - i + 1;
                    String candidate = s.substring(i, j + 1);
                    if (len < minLen || (len == minLen && candidate.compareTo(result) < 0)) {
                        minLen = len;
                        result = candidate;
                    }
                    break; 
                }
            }
        }
        return result;
    }
}