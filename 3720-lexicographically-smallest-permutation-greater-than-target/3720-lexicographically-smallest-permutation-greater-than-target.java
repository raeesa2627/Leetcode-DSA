class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] baseCnt = new int[26];
        for (char ch : s.toCharArray()) baseCnt[ch - 'a']++;  
        int[] freq = new int[26];
        int maxK = n;
        for (int i = 0; i < n; i++) {
            int c = target.charAt(i) - 'a';
            freq[c]++;
            if (freq[c] > baseCnt[c]) {
                maxK = i;
                break;
            }
        }   
        int startK = (maxK == n) ? n - 1 : maxK;      
        int[] cnt = baseCnt.clone();
        for (int i = 0; i < startK; i++) {
            cnt[target.charAt(i) - 'a']--;
        }    
        int k = startK;
        while (k >= 0) {
            int tChar = target.charAt(k) - 'a';
            int found = -1;
            for (int c = tChar + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    found = c;
                    break;
                }
            }
            if (found != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, k);
                sb.append((char) ('a' + found));
                cnt[found]--;
                for (int c = 0; c < 26; c++) {
                    for (int cc = 0; cc < cnt[c]; cc++) {
                        sb.append((char) ('a' + c));
                    }
                }
                return sb.toString();
            }
            if (k == 0) break;
            cnt[target.charAt(k - 1) - 'a']++;
            k--;
        }
        return "";
    }
}