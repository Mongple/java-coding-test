package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Bfs1926 {
    /*
     * <a href="https://www.acmicpc.net/problem/1926"></a>
     * 문제
     * 어떤 큰 도화지에 그림이 그려져 있을 때, 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하여라. 단, 그림이라는 것은 1로 연결된 것을 한 그림이라고 정의하자. 가로나 세로로 연결된 것은 연결이 된 것이고 대각선으로 연결이 된 것은 떨어진 그림이다. 그림의 넓이란 그림에 포함된 1의 개수이다.
     *
     * 입력
     * 첫째 줄에 도화지의 세로 크기 n(1 ≤ n ≤ 500)과 가로 크기 m(1 ≤ m ≤ 500)이 차례로 주어진다. 두 번째 줄부터 n+1 줄 까지 그림의 정보가 주어진다. (단 그림의 정보는 0과 1이 공백을 두고 주어지며, 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다)
     *
     * 출력
     * 첫째 줄에는 그림의 개수, 둘째 줄에는 그 중 가장 넓은 그림의 넓이를 출력하여라. 단, 그림이 하나도 없는 경우에는 가장 넓은 그림의 넓이는 0이다.
     */

    /*
        1. 설계 : 2중 for문, 방문x -> BFS
        2. 시간 : O(V+E)
            V : 500 * 500
            E : V * 4
            B(V+E) : 500 * 500 + (500 * 500 * 4) = 1,000,000
            100만 < 2억 >> 가능
        3. 자료구조
     */
    
    // 상하좌우
    private final static int[] DR = {-1, 1, 0, 0};     // 상하좌우
    private final static int[] DC = {0, 0, -1, 1};     // 상하좌우
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int m = Integer.parseInt(st.nextToken()); // 행 수(6)
        int n = Integer.parseInt(st.nextToken()); // 열 수(5)

        int[][] input = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        solution(input);
    }
    
    private static void solution(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int cnt = 0;    // 그림 개수
        int maxSize = 0;    // 그림의 넓이
        
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                
                if (grid[r][c] == 1 && !visited[r][c]) {
                    cnt++;
                    
                    maxSize = Math.max(bfs(r, c, grid, visited), maxSize);
                }
                
            }
        }
        
        System.out.println(cnt);
        System.out.println(maxSize);
        
    }

    public static int bfs(int r, int c, int[][] grid, boolean[][] visited) {
        int rowLength = grid.length;
        int colLength = grid[0].length;
        
        // 현재 행렬정보 큐에저장 및 방문처리
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r, c});
        visited[r][c] = true;
        int returnSize = 1;

        while (!q.isEmpty()) {
            int[] cur = q.pollFirst(); // BFS: 큐 앞에서 꺼내기
            int currentRow = cur[0];
            int currentColumn = cur[1];

            // 상하좌우 탐색이므로 4번
            for (int k = 0; k < 4; k++) {
                int nextRow = currentRow + DR[k];
                int nextColumn = currentColumn + DC[k];

                // grid 크기 제한
                if (0 <= nextRow && nextRow < rowLength 
                        && 0 <= nextColumn && nextColumn < colLength) {
                    
                    if (grid[nextRow][nextColumn] == 1 && !visited[nextRow][nextColumn]) {
                        visited[nextRow][nextColumn] = true;
                        returnSize += 1;
                        q.addLast(new int[]{nextRow, nextColumn});
                    }
                }
            }
        }
        
        return returnSize;
    }
}