class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean hasOdd = false, hasEven = false;
        int minOdd = Integer.MAX_VALUE, minEven = Integer.MAX_VALUE;
        for (int x : nums1) {
            if (x % 2 == 0) {
                hasEven = true;
                minEven = Math.min(minEven, x);
            } else {
                hasOdd = true;
                minOdd = Math.min(minOdd, x);
            }
        }
        boolean allEvenPossible = !hasOdd;
        boolean allOddPossible = !hasEven || (minOdd < minEven);
        return allEvenPossible || allOddPossible;
    }
}