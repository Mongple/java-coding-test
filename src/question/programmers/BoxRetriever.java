package question.programmers;

import java.util.ArrayList;
import java.util.List;

public class BoxRetriever {

    public static void main(String[] args) {
        System.out.println("solution : " + solution(22, 6, 8));
    }

    /*
        Programmers. 택배 상자 꺼내기
        URL : <a href="https://school.programmers.co.kr/learn/courses/30/lessons/389478" />
        ---------------------------

        [문제 설명]
        택배 상자
        - 1~N

        택배 쌓기
        - 왼쪽에서 오른쪽으로 w개 쌓기
        - 그 위에 오른쪽에서 왼쪽으로 w개 쌓기
        - 반복

        택배 꺼내기
        - 입력된 상자번호(A)를 꺼내기
        - 그 위에 쌓인 모든 상자부터 꺼내야 함

        [입력]
        N : 창고에 있는 택배 상자의 수
        W : 가로로 놓는 상자 수
        num : 꺼내려는 상자 번호

        [출력]
        꺼래려는 상자를 포함해서 총 꺼내야하는 상자의 수

        [제한사항]
        2 <= N <= 100
        1 <= W <= 10
        1 <= num <= N
    */
    public static int solution(int n, int w, int num) {
        int answer = 0;

        List<List<Integer>> boxRows = new ArrayList<>();

        for (int i = 1; i <= w; i++) {
            boxRows.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {

            int rowNum = (i - 1) / w;

            boolean isReverse = rowNum % 2 != 0;

            int columnRow;

            if (!isReverse) {
                columnRow = i - (w * rowNum) - 1;
            } else {
                columnRow = (w - 1) - ((i - 1) - (w * rowNum));
            }

            boxRows.get(columnRow).add(i);
        }

        for (List<Integer> boxRow : boxRows) {
            boolean hasNum = false;

            for (Integer data : boxRow) {
                hasNum = hasNum || data == num;

                if (hasNum) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
