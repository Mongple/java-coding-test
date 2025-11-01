package algorithm.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFS {
    // 격자 크기: 행(n), 열(m)
    static int n, m;
    // 0/1 격자 (1은 군집의 일부, 0은 빈 칸)
    static int[][] grid;
    // 방문 여부 표시 배열
    static boolean[][] visited;
    // 4방향(상하좌우) 이동을 위한 행/열 변화량
    static final int[] dr = {-1, 1, 0, 0}; // 행 변화: 위, 아래, 그대로, 그대로
    static final int[] dc = {0, 0, -1, 1}; // 열 변화: 그대로, 그대로, 왼쪽, 오른쪽

    static final int[] dr8 = {-1,-1, 0, 1, 1, 1, 0,-1}; // 상부터 시작한 시계방향
    static final int[] dc8 = { 0, 1, 1, 1, 0,-1,-1,-1};
    
    // (r, c)에서 시작하여 상하좌우로 연결된 모든 1을 방문하고 그 크기를 반환하는 DFS
    static int dfs(int r, int c) {
        visited[r][c] = true;   // 현재 칸 방문 처리
        int size = 1;           // 현재 칸 포함 크기 1부터 시작

        // 4방향 이웃 확인
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k]; // 이웃 행
            int nc = c + dc[k]; // 이웃 열

            // 경계 밖이면 무시
            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

            // 이웃이 1이고 아직 방문하지 않았다면 계속 확장
            if (grid[nr][nc] == 1 && !visited[nr][nc]) {
                size += dfs(nr, nc); // 재귀적으로 크기 누적
            }
        }
        return size; // 연결 컴포넌트(군집) 크기 반환
    }

    public static void main(String[] args) {
        // 샘플 격자 초기화
        grid = new int[][]{
                {0, 1, 1, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1}
        };

        n = grid.length;       // 행 개수
        m = grid[0].length;    // 열 개수
        visited = new boolean[n][m]; // 방문 배열 생성

        int count = 0;                // 군집(컴포넌트) 수
        List<Integer> sizes = new ArrayList<>(); // 각 군집의 크기 저장

        // 모든 칸을 순회하면서 새 군집의 시작점을 찾음
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                // 값이 1이고 아직 방문하지 않았다면 새로운 군집
                if (grid[r][c] == 1 && !visited[r][c]) {
                    int sz = dfs(r, c); // 군집 크기 계산
                    sizes.add(sz);      // 크기 기록
                    count++;            // 군집 수 증가
                }
            }
        }

        // 군집 크기 오름차순 정렬
        Collections.sort(sizes);

        // 결과 출력
        System.out.println("군집 수: " + count);
        System.out.println("군집 크기들: " + sizes);
        System.out.println("최대 군집: " + (sizes.isEmpty() ? 0 : sizes.get(sizes.size() - 1)));
    }

//    public static void main(String[] args) {
//        int[] dr = {-1, 1, 0, 0}; // 상하
//        int[] dc = {0, 0, -1, 1}; // 좌우
//
//        char[][] grid = {
//                {'5', 'X', '1', '2'},
//                {'X', 'X', '2', '3'},
//                {'4', 'X', '5', '9'},
//                {'8', '7', 'X', '5'}
//        };
//
//        int sum = 0;
//        for(int r = 0; r < grid.length; r++)
//            for(int c = 0; c < grid[0].length; c++) {
//                if( grid[r][c] == 'X') {
//                    for(int i = 0; i < 4; i++) {
//                        int nr = r + dr[i];
//                        int nc = c + dc[i];
//
//                        if(nr >= 0 && nr < grid[0].length && nc >= 0 && nc < grid[0].length && grid[nr][nc] != 'X') {
//                            sum+=grid[nr][nc] - '0';
//                            grid[nr][nc] = '0';
//
//                        }
//                    }
//                }
//            }
//        System.out.println(sum);
//    }
}

