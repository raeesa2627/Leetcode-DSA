class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        
        // Sort indices by their value
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);
        
        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            int j = i;
            // Extend the group while consecutive sorted values are within limit
            while (j + 1 < n && nums[idx[j + 1]] - nums[idx[j]] <= limit) {
                j++;
            }
            
            // Collect original indices in this group, sort them
            List<Integer> positions = new ArrayList<>();
            for (int k = i; k <= j; k++) positions.add(idx[k]);
            Collections.sort(positions);
            
            // Assign sorted values to sorted positions
            for (int k = i; k <= j; k++) {
                result[positions.get(k - i)] = nums[idx[k]];
            }
            
            i = j + 1;
        }
        
        return result;
    }
}