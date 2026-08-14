class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            int c = s.charAt(right) - 'a';
            count[c]++;
            while (count[c] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}