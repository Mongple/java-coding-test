package question.baekjoon;

import java.util.*;
import java.io.*;

public class Dfs2667 {
    /*
        <a href="https://www.acmicpc.net/problem/2667"></a>
        
        1. 설계 : 2중 for문, 방문x -> BFS
        2. 시간 : O(V+E)
            V : N^2
            E : 4N^2
            V+E : 5N^2 ~= N^2 = 625 >>> 가능
                - 빅오표기법에서는 상수는 제외 즉 5는 생략 후 계산
        3. 자료구조
     */
    
    // 상하좌우
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader sb = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(sb.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(sb.readLine());
            String[] rowArr = st.nextToken().split("");
            
            try {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(rowArr[j]);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
        
        solution(grid);
    }
    
    private static void solution(int[][] grid) {
        
        int gridRowLength = grid.length;
        int gridColumnLength = grid[0].length;
        
        boolean[][] visited = new boolean[gridRowLength][gridColumnLength];
        List<Integer> countList = new ArrayList<>();
        
        for (int r = 0; r < gridRowLength; r++) {
            for (int c = 0; c < gridColumnLength; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    countList.add(dfs(r, c, grid, visited));
                }
            }
        }
        
        System.out.println(countList.size());
        countList.stream()
                .sorted()
                .forEach(System.out::println);
    }
    
    private static int dfs(int r, int c, int[][] grid, boolean[][] visited) {
        
        int cnt = 1;
        visited[r][c] = true;
        
        for (int i = 0; i < 4; i++) {
            int nr = r + DR[i];
            int nc = c + DC[i];
            
            // grid를 벗어나지 않음
            if (nr >= 0 && nr < grid.length 
                    && nc >= 0 && nc < grid[0].length) {
                if (grid[nr][nc] == 1 && !visited[nr][nc]) {
                    cnt += dfs(nr, nc, grid, visited);
                }
            }
        }
        
        return cnt;
    }
}

