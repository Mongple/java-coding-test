package question.programmers;

import java.util.Arrays;

public class CleanupDesktop {
    
    public static void main(String[] args) {
        String[] wallpaper = {
                "..........", 
                ".....#....", 
                "......##..", 
                "...##.....", 
                "....#....."
        };
        System.out.println("solution : " + Arrays.toString(solution(wallpaper)));
    }

    /**
     * Programmers. 바탕화면 정리
     * URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/161990"></a>
     *
     * [문제 설명]
     * 코딩테스트를 준비하는 머쓱이는 프로그래머스에서 문제를 풀고 나중에 다시 코드를 보면서 공부하려고 작성한 코드를 컴퓨터 바탕화면에 아무 위치에나 저장해 둡니다. 저장한 코드가 많아지면서 머쓱이는 본인의 컴퓨터 바탕화면이 너무 지저분하다고 생각했습니다. 프로그래머스에서 작성했던 코드는 그 문제에 가서 다시 볼 수 있기 때문에 저장해 둔 파일들을 전부 삭제하기로 했습니다.
     *
     * 컴퓨터 바탕화면은 각 칸이 정사각형인 격자판입니다. 이때 컴퓨터 바탕화면의 상태를 나타낸 문자열 배열 wallpaper가 주어집니다. 파일들은 바탕화면의 격자칸에 위치하고 바탕화면의 격자점들은 바탕화면의 가장 왼쪽 위를 (0, 0)으로 시작해 (세로 좌표, 가로 좌표)로 표현합니다. 빈칸은 ".", 파일이 있는 칸은 "#"의 값을 가집니다. 드래그를 하면 파일들을 선택할 수 있고, 선택된 파일들을 삭제할 수 있습니다. 머쓱이는 최소한의 이동거리를 갖는 한 번의 드래그로 모든 파일을 선택해서 한 번에 지우려고 하며 드래그로 파일들을 선택하는 방법은 다음과 같습니다.
     *
     * 드래그는 바탕화면의 격자점 S(lux, luy)를 마우스 왼쪽 버튼으로 클릭한 상태로 격자점 E(rdx, rdy)로 이동한 뒤 마우스 왼쪽 버튼을 떼는 행동입니다. 이때, "점 S에서 점 E로 드래그한다"고 표현하고 점 S와 점 E를 각각 드래그의 시작점, 끝점이라고 표현합니다.
     *
     * 점 S(lux, luy)에서 점 E(rdx, rdy)로 드래그를 할 때, "드래그 한 거리"는 |rdx - lux| + |rdy - luy|로 정의합니다.
     *
     * 점 S에서 점 E로 드래그를 하면 바탕화면에서 두 격자점을 각각 왼쪽 위, 오른쪽 아래로 하는 직사각형 내부에 있는 모든 파일이 선택됩니다.
     *
     * 예를 들어 wallpaper = [".#...", "..#..", "...#."]인 바탕화면을 그림으로 나타내면 다음과 같습니다.
     *
     * [제한사항]
     * 1 ≤ wallpaper의 길이 ≤ 50
     * 1 ≤ wallpaper[i]의 길이 ≤ 50
     * wallpaper의 모든 원소의 길이는 동일합니다.
     * wallpaper[i][j]는 바탕화면에서 i + 1행 j + 1열에 해당하는 칸의 상태를 나타냅니다.
     * wallpaper[i][j]는 "#" 또는 "."의 값만 가집니다.
     * 바탕화면에는 적어도 하나의 파일이 있습니다.
     * 드래그 시작점 (lux, luy)와 끝점 (rdx, rdy)는 lux < rdx, luy < rdy를 만족해야 합니다.
     */

    public static int[] solution(String[] wallpaper) {
//        최소값을 찾을 때 초기값을 매우 큰 값(MAX_VALUE)으로 두면, 첫 값과 비교해 바로 더 작은 실제 값으로 갱신
//        - 최소 탐색: min = +∞로 시작 → 만나는 값이 무엇이든 min = Math.min(min, value)로 갱신됨
//        - 최대 탐색: max = -∞로 시작 → 만나는 값이 무엇이든 max = Math.max(max, value)로 갱신됨
//        즉, lux/luy는 “처음 만나는 ‘#’ 좌표로 반드시 갱신되게” 하려면 MAX_VALUE로 시작하는 게 안전합니다.
        int lux = 50, luy = 50;   // 최소 row, column
        int rdx = 0, rdy = 0;     // 최대 row + 1, column + 1

        for (int i = 0; i < wallpaper.length; i++) {
            String row = wallpaper[i];
            
            for (int j = 0; j < row.length(); j++) {
                if (row.charAt(j) == '#') {
                    
//                    System.out.print("lux : " + lux + ", luy : " + luy + ", rdx : " + rdx + ", rdy : " + rdy);
                    
                    if (i < lux) lux = i;
                    if (j < luy) luy = j;
                    if (i + 1 > rdx) rdx = i + 1;
                    if (j + 1 > rdy) rdy = j + 1;

//                    System.out.println(" ----> lux : " + lux + ", luy : " + luy + ", rdx : " + rdx + ", rdy : " + rdy);
                }
            }
        }
        return new int[]{lux, luy, rdx, rdy};
    }
}
