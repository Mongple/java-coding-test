package question.programmers;

import java.util.ArrayDeque;

public class BfsGameMap {
    /*
        <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1844" />
     */
    
    public static void main(String[] args) {
        int[][] maps = {
                {1, 0, 1, 1, 1}, 
                {1, 0, 1, 0, 1}, 
                {1, 0, 1, 1, 1}, 
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };
        
        System.out.println("solution : " + solution(maps));
    }

    // 상하좌우
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    
    public static int solution(int[][] maps) {
        int n = maps.length, m = maps[0].length;
        int[][] dist = new int[n][m];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        dist[0][0] = 1;
        q.add(new int[]{0, 0});
        
        int result = -1;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if (r == n - 1 && c == m - 1) {
                result = dist[r][c];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                if (maps[nr][nc] == 0 || dist[nr][nc] != 0) continue;

                dist[nr][nc] = dist[r][c] + 1;
                q.add(new int[]{nr, nc});
            }
        }
        
        return result;
    }
}
