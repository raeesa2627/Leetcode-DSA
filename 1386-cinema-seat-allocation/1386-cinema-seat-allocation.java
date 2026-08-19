class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();
        for (int[] rs : reservedSeats) {
            int row = rs[0], seat = rs[1];
            if (seat < 2 || seat > 9) continue; 
            int bit = 1 << (seat - 2);
            rowMask.put(row, rowMask.getOrDefault(row, 0) | bit);
        }
        
        int leftMask = 0b00001111;  
        int midMask  = 0b00111100;  
        int rightMask= 0b11110000;  
        
        int count = 0;
        for (int mask : rowMask.values()) {
            if ((mask & leftMask) == 0) {
                count++;
            } else if ((mask & rightMask) == 0) {
                count++;
            } else if ((mask & midMask) == 0) {
                count++;
            }
        }
        
        int rowsWithReservations = rowMask.size();
        count += (long) (n - rowsWithReservations) * 2 > Integer.MAX_VALUE ? 0 : (n - rowsWithReservations) * 2;
        
        return count;
    }
}