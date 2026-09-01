import java.util.*;
class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int sr = -1, sc = -1;
        List<int[]> litterCells = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') { sr = i; sc = j; }
                else if (c == 'L') litterCells.add(new int[]{i, j});
            }
        }
        
        int totalLitter = litterCells.size();
        if (totalLitter == 0) return 0;
        
        int fullMask = (1 << totalLitter) - 1;
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) Arrays.fill(row, -1);
        for (int idx = 0; idx < litterCells.size(); idx++) {
            int[] cell = litterCells.get(idx);
            litterIndex[cell[0]][cell[1]] = idx;
        }
        
        boolean[][][][] visited = new boolean[m][n][energy + 1][fullMask + 1];
        Queue<int[]> queue = new LinkedList<>();
        
        int startMask = 0;
        queue.offer(new int[]{sr, sc, energy, startMask});
        visited[sr][sc][energy][startMask] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1], e = cur[2], mask = cur[3];
                
                if (mask == fullMask) return moves;
                if (e == 0) continue; // stuck, no energy to move further
                
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    
                    char ch = classroom[nr].charAt(nc);
                    if (ch == 'X') continue;
                    
                    int newE = e - 1;
                    if (ch == 'R') newE = energy; // reset to full
                    
                    int newMask = mask;
                    if (ch == 'L') {
                        int idx = litterIndex[nr][nc];
                        if (idx >= 0) newMask = mask | (1 << idx);
                    }
                    
                    if (!visited[nr][nc][newE][newMask]) {
                        visited[nr][nc][newE][newMask] = true;
                        queue.offer(new int[]{nr, nc, newE, newMask});
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}