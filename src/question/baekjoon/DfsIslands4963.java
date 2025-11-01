package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DfsIslands4963 {
    
    /*
        <a href="https://www.acmicpc.net/problem/4963"></a>
     */
    private static int[] DR = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] DC = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        // 1 : 땅, 0 : 바다
        List<int[][]> gridList = getGridList();
        
        for (int[][] grid : gridList) {
            
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            int count = 0;
            
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    
                    if (visited[r][c]) continue;
                    if (grid[r][c] != 1) continue;
                    
                    dfs(grid, visited, r, c);
                    count++;
                }
            }
            
            System.out.println(count);
        }
    }
    
    private static List<int[][]> getGridList() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<int[][]> gridList = new ArrayList<>();

        while (true) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken()); // 지도 너비
            int h = Integer.parseInt(st.nextToken()); // 지도 높이

            if (h == 0 && w == 0) {
                break;
            }

            int[][] grid = new int[h][w];

            for (int r = 0; r < h; r++) {

                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < w; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            gridList.add(grid);
        }
        
        return gridList;
    }
    
    private static void dfs(int[][] grid, boolean[][] visited, int r, int c) {
        
        if (r == grid.length && c == grid[0].length) return;

        visited[r][c] = true;
        
        for (int i = 0; i < DR.length; i++) {
            
            int  nr = r + DR[i];
            int  nc = c + DC[i];
            
            if (nr < 0 || nr >= grid.length) continue;
            if (nc < 0 || nc >= grid[nr].length) continue;
            if (visited[nr][nc]) continue;
            if (grid[nr][nc] != 1) continue;
            
            dfs(grid, visited, nr, nc);
        }
    }
}
