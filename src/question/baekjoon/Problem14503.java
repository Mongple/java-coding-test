package question.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem14503 {

    /*
        <a href="https://www.acmicpc.net/problem/14503"></a>
     */

    private static final int[] DR = {-1, 0, 1, 0};  // 북동남서
    private static final int[] DC = {0, 1, 0, -1};
    
    private static int N;
    private static int M;
    private static int[][] ROOMARR;
    private static int R;   // 현재좌표 R
    private static int C;   // 현재좌표 C
    private static int D;   // 방향 북 : 0, 동 : 1, 남 : 2, 서 : 3
    private static int CLEANCOUNT = 0;
    
    public static void main(String[] args) throws IOException {
        solution();
    }
    
    private static void setInputData() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] mnArr = br.readLine().split(" ");
        N = Integer.parseInt(mnArr[0]);
        M = Integer.parseInt(mnArr[1]);
        ROOMARR = new int[N][M];

        String[] rcdArr = br.readLine().split(" ");
        R = Integer.parseInt(rcdArr[0]);
        C = Integer.parseInt(rcdArr[1]);
        D = Integer.parseInt(rcdArr[2]);
        
        for (int r = 0; r < N; r++) {

            String[] colArr = br.readLine().split(" ");
            
            for (int c = 0; c < M; c++) {
                ROOMARR[r][c] = Integer.parseInt(colArr[c]);
            }
        }
    }
    
    private static void solution() throws IOException {
        setInputData();
        recur(R, C);
        System.out.println(CLEANCOUNT);
    }
    
    private static void recur(int r, int c) {
        // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
        if (ROOMARR[r][c] == 0) {
            ROOMARR[r][c] = 2;
            CLEANCOUNT++;
        }

        Integer noCleanIndex = getNoCleanIndex(r, c);

        // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
        if (noCleanIndex == null) {
            
            // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
            int back = (D + 2) % 4;
            int nr = r + DR[back];
            int nc = c + DC[back];

            if (nr >= 0 && nr < ROOMARR.length
                    && nc >= 0 && nc < ROOMARR[0].length
                    && ROOMARR[nr][nc] != 1) {
                recur(nr, nc);
            }
            
            // 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
            return;
        } else {
            // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            
            // 반시계방향 회전
            D = noCleanIndex;
            int nr = r + DR[D];
            int nc = c + DC[D];

            // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
            if (nr >= 0 && nr < ROOMARR.length
                    && nc >= 0 && nc < ROOMARR[0].length
                    && ROOMARR[nr][nc] == 0) {
                recur(nr, nc);
            }
        }
    }

    private static Integer getNoCleanIndex(int r, int c) {
        // 현재 방향 D를 기준으로 반시계로 최대 4번 회전하며 앞칸이 미청소(0)인지 확인
        for (int i = 0; i < 4; i++) {
            int nextDir = (D + 3 - i) % 4; // 반시계
            int nr = r + DR[nextDir];
            int nc = c + DC[nextDir];

            if (nr >= 0 && nr < ROOMARR.length
                    && nc >= 0 && nc < ROOMARR[0].length
                    && ROOMARR[nr][nc] == 0) {
                return nextDir;
            }
        }
        
        return null;
    }
}
