class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) count[d]++;
        int total = 0;
        for (int hundreds = 1; hundreds <= 9; hundreds++) {
            if (count[hundreds] == 0) continue;
            count[hundreds]--;
            for (int tens = 0; tens <= 9; tens++) {
                if (count[tens] == 0) continue;
                count[tens]--;
                for (int units = 0; units <= 8; units += 2) {
                    if (count[units] == 0) continue;
                    total++;
                }
                count[tens]++;
            }
            count[hundreds]++;
        }
        return total;
    }
}