package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bfs2178 {
    // 최소거리
    /*
     * <a href="https://www.acmicpc.net/problem/2178"></a>
     */
    
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n + 1][m + 1];
        boolean[][] visited = new boolean[n + 1][m + 1];
        
        for (int r = 1; r <= n; r++) {
            String[] arr = br.readLine().split("");
            for (int c = 1; c <= m; c++) {
                grid[r][c] = Integer.parseInt(arr[c - 1]);
            }
        }
        
        bfs(grid, visited, 1, 1);
        
        System.out.println(grid[n][m]);
    }
    
    private static void bfs(int[][] grid, boolean[][] visited, int r, int c) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{r, c});
        visited[r][c] = true;
        
        while (!deque.isEmpty()) {
            
            if (r == grid.length && c == grid[0].length) break;
            
            int[] point = deque.pollFirst();
            
            for (int i = 0; i < DR.length; i++) {
                int nr = point[0] + DR[i];
                int nc = point[1] + DC[i];
                
                if (nr < 1 || nr >= grid.length) continue;
                if (nc < 1 || nc >= grid[0].length) continue;
                if (visited[nr][nc]) continue;
                if (grid[nr][nc] == 0) continue;
                
                visited[nr][nc] = true;
                grid[nr][nc] = grid[point[0]][point[1]] + 1;
                deque.add(new int[]{nr, nc});
            }
        }
    }
}
