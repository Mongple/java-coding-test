package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BfsCheese2636 {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] grid = new int[R][C];
        int timeCount = 0;
        int size = 0;

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                grid[r][c] = Integer.parseInt(st.nextToken());
                if (grid[r][c] == 1) size++;
            }
        }

        List<Integer> sizeList = new ArrayList<>();
        sizeList.add(size);

        while (size > 0) {
            // 1. 외부공기 -1 처리
            bfsMarkAir(grid);

            // 2. 녹을 예정 치즈 목록
            List<int[]> meltList = bfsMelt(grid);

            // 3. 녹을 치즈가 없으면 종료
            if (meltList.isEmpty()) break;

            // 4. 녹이기 (실제 grid 값 변경)
            for (int[] p : meltList) {
                grid[p[0]][p[1]] = 0;
            }

            // 5. 치즈 수 업데이트
            size -= meltList.size();
            sizeList.add(size);

            // 6. 외부공기 초기화 (-1 → 0)
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (grid[i][j] == -1) grid[i][j] = 0;
                }
            }

            timeCount++;
        }

        System.out.println(timeCount); // 총 걸린 시간
        System.out.println(sizeList.get(sizeList.size() - 2)); // 다 녹기 전 치즈 수
    }

    private static void bfsMarkAir(int[][] grid) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{0, 0});

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[0][0] = true;

        while (!deque.isEmpty()) {
            int[] point = deque.pollFirst();

            for (int i = 0; i < DR.length; i++) {
                int nr = point[0] + DR[i];
                int nc = point[1] + DC[i];

                if (nr < 0 || nr >= grid.length) continue;
                if (nc < 0 || nc >= grid[0].length) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;

                if (grid[nr][nc] == 0) {
                    grid[nr][nc] = -1;
                    deque.addLast(new int[]{nr, nc});
                }
            }
        }
    }

    private static List<int[]> bfsMelt(int[][] grid) {
        List<int[]> meltList = new ArrayList<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] != 1) continue;

                for (int i = 0; i < DR.length; i++) {
                    int nr = r + DR[i];
                    int nc = c + DC[i];

                    if (nr < 0 || nr >= grid.length) continue;
                    if (nc < 0 || nc >= grid[0].length) continue;

                    if (grid[nr][nc] == -1) {
                        meltList.add(new int[]{r, c});
                        break;
                    }
                }
            }
        }
        return meltList;
    }
}
